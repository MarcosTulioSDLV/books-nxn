package com.springboot.books_nxn.services;

import com.springboot.books_nxn.dtos.BookRequestDto;
import com.springboot.books_nxn.dtos.BookResponseDto;
import com.springboot.books_nxn.dtos.UpdateBookRequestDto;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

@Service
public interface BookService {

    Page<BookResponseDto> getAllBooks(Pageable pageable);

    BookResponseDto getBookById(Long id);

    @Transactional
    BookResponseDto addBook(BookRequestDto bookRequestDto);

    @Transactional
    BookResponseDto updateBook(UpdateBookRequestDto updateBookRequestDto,Long id);

    @Transactional
    void removeBook(Long id);

}
