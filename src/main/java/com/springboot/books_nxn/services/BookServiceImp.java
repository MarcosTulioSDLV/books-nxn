package com.springboot.books_nxn.services;

import com.springboot.books_nxn.dtos.BookRequestDto;
import com.springboot.books_nxn.dtos.BookResponseDto;
import com.springboot.books_nxn.dtos.UpdateBookRequestDto;
import com.springboot.books_nxn.exceptions.BookNotFoundException;
import com.springboot.books_nxn.exceptions.BookTitleExistsException;
import com.springboot.books_nxn.mappers.BookMapper;
import com.springboot.books_nxn.models.Author;
import com.springboot.books_nxn.models.Book;
import com.springboot.books_nxn.repositories.BookRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImp implements BookService{

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    private final AuthorService authorService;

    @Autowired
    public BookServiceImp(BookRepository bookRepository, BookMapper bookMapper, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.authorService = authorService;
    }


    @Override
    public Page<BookResponseDto> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable).map(bookMapper::toBookResponseDto);
    }

    @Override
    public BookResponseDto getBookById(Long id) {
        Book book= findBookById(id);
        return bookMapper.toBookResponseDto(book);
    }

    private Book findBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(()->new BookNotFoundException("Book with id: " + id + " not found!"));
    }

    @Override
    @Transactional
    public BookResponseDto addBook(BookRequestDto bookRequestDto) {
        Book book= bookMapper.toBook(bookRequestDto);
        validateUniqueData(book);

        List<Long> authorIds= bookRequestDto.getAuthorIds();
        List<Author> authors= authorService.findAuthorsByIds(authorIds);

        book.getAuthors().addAll(authors);
        return bookMapper.toBookResponseDto(bookRepository.save(book));
    }

    private void validateUniqueData(Book book) {
        if(bookRepository.existsByTitleIgnoreCase(book.getTitle())){
            throw new BookTitleExistsException("Book title: "+ book.getTitle()+" already exists!");
        }
    }

    @Override
    @Transactional
    public BookResponseDto updateBook(UpdateBookRequestDto updateBookRequestDto, Long id) {
        Book book= bookMapper.toBook(updateBookRequestDto);
        book.setId(id);

        Book recoveredBook= findBookById(id);

        validateUpdateConflict(book,recoveredBook);

        //Note: Only for update also relationship property: authors (in this case, we could also use BookRequestDto, and UpdateBookRequestDto wouldn't be required)
        /*List<Long> authorIds= bookRequestDto.getAuthorIds();
        List<Author> authors= authorService.findAuthorsByIds(authorIds);
        recoveredBook.setAuthors(authors);*/

        BeanUtils.copyProperties(book,recoveredBook,"authors");//Ignore the relationship property "authors"
        return bookMapper.toBookResponseDto(recoveredBook);
    }

    private void validateUpdateConflict(Book book,Book recoveredBook) {
        if(bookTitleExistsAndBelongsToAnotherInstance(book.getTitle(),recoveredBook)){
            throw new BookTitleExistsException("Book title: "+ book.getTitle()+" already exists!");
        }
    }

    private boolean bookTitleExistsAndBelongsToAnotherInstance(String title,Book recoveredBook) {
        return bookRepository.existsByTitleIgnoreCase(title) && !title.equalsIgnoreCase(recoveredBook.getTitle());
    }

    @Override
    @Transactional
    public void removeBook(Long id) {
        Book book= findBookById(id);
        bookRepository.delete(book);
    }

}
