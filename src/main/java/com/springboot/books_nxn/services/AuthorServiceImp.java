package com.springboot.books_nxn.services;

import com.springboot.books_nxn.dtos.AuthorRequestDto;
import com.springboot.books_nxn.dtos.AuthorResponseDto;
import com.springboot.books_nxn.exceptions.AuthorFullNameExistsException;
import com.springboot.books_nxn.exceptions.AuthorNotFoundException;
import com.springboot.books_nxn.mappers.AuthorMapper;
import com.springboot.books_nxn.models.Author;
import com.springboot.books_nxn.repositories.AuthorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImp implements AuthorService{

    private final AuthorRepository authorRepository;

    private final AuthorMapper authorMapper;

    @Autowired
    public AuthorServiceImp(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }


    @Override
    public Page<AuthorResponseDto> getAllAuthors(Pageable pageable) {
        return authorRepository.findAll(pageable).map(authorMapper::toAuthorResponseDto);
    }

    @Override
    public AuthorResponseDto getAuthorById(Long id) {
        Author author= findAuthorById(id);
        return authorMapper.toAuthorResponseDto(author);
    }

    private Author findAuthorById(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException("Author with id: " + id + " not found!"));
    }

    public List<Author> findAuthorsByIds(List<Long> authorIds){
        return authorIds.stream().map(this::findAuthorById).toList();
    }

    @Override
    @Transactional
    public AuthorResponseDto addAuthor(AuthorRequestDto authorRequestDto) {
        Author author= authorMapper.toAuthor(authorRequestDto);
        validateUniqueData(author);
        return authorMapper.toAuthorResponseDto(authorRepository.save(author));
    }

    private void validateUniqueData(Author author) {
        if(authorRepository.existsByFullNameIgnoreCase(author.getFullName())){
            throw new AuthorFullNameExistsException("Author with FullName: "+ author.getFullName()+" already exists!");
        }
    }

    @Override
    @Transactional
    public AuthorResponseDto updateAuthor(AuthorRequestDto authorRequestDto,Long id) {
        Author author= authorMapper.toAuthor(authorRequestDto);
        author.setId(id);

        Author recoveredAuthor= findAuthorById(id);

        validateUpdateConflict(author,recoveredAuthor);

        BeanUtils.copyProperties(author,recoveredAuthor,"books");//Ignore relationship property to copy
        return authorMapper.toAuthorResponseDto(recoveredAuthor);
    }

    private void validateUpdateConflict(Author author,Author recoveredAuthor) {
        if(authorFullNameExistsAndBelongsToAnotherInstance(author.getFullName(),recoveredAuthor)){
            throw new AuthorFullNameExistsException("Author with FullName: "+ author.getFullName()+" already exists!");
        }
    }

    private boolean authorFullNameExistsAndBelongsToAnotherInstance(String fullName,Author recoveredAuthor) {
        return authorRepository.existsByFullNameIgnoreCase(fullName) && !fullName.equalsIgnoreCase(recoveredAuthor.getFullName());
    }


    //Not used because if remove authors, it could let books without any author (incorrect behavior).
    /*@Override
    @Transactional
    public void removeAuthor(Long id) {
        Author author= findAuthorById(id);
        authorRepository.delete(author);
    }*/


}
