package com.springboot.books_nxn.services;

import com.springboot.books_nxn.dtos.AuthorRequestDto;
import com.springboot.books_nxn.dtos.AuthorResponseDto;
import com.springboot.books_nxn.models.Author;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorService {

    Page<AuthorResponseDto> getAllAuthors(Pageable pageable);

    AuthorResponseDto getAuthorById(Long id);

    List<Author> findAuthorsByIds(List<Long> authorIds);

    @Transactional
    AuthorResponseDto addAuthor(AuthorRequestDto authorRequestDto);

    @Transactional
    AuthorResponseDto updateAuthor(AuthorRequestDto authorRequestDto, Long id);


    /*@Transactional
    void removeAuthor(Long id);*/

}
