package com.springboot.books_nxn.controllers;

import com.springboot.books_nxn.dtos.AuthorRequestDto;
import com.springboot.books_nxn.dtos.AuthorResponseDto;
import com.springboot.books_nxn.models.Author;
import com.springboot.books_nxn.services.AuthorService;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }


    @GetMapping(value = "/authors")
    public ResponseEntity<Page<AuthorResponseDto>> getAllAuthors(@PageableDefault(size = 5) Pageable pageable){
        return ResponseEntity.ok(authorService.getAllAuthors(pageable));
    }

    @GetMapping(value = "/authors/{id}")
    public ResponseEntity<AuthorResponseDto> getAuthorById(@PathVariable Long id){
        return ResponseEntity.ok(authorService.getAuthorById(id));
    }

    @PostMapping(value = "/authors")
    public ResponseEntity<AuthorResponseDto> addAuthor(@RequestBody @Valid AuthorRequestDto authorRequestDto){
        return new ResponseEntity<>(authorService.addAuthor(authorRequestDto),HttpStatus.CREATED);
    }

    @PutMapping(value = "/authors/{id}")
    public ResponseEntity<AuthorResponseDto> updateAuthor(@RequestBody @Valid AuthorRequestDto authorRequestDto,
                                                          @PathVariable Long id){
        return ResponseEntity.ok(authorService.updateAuthor(authorRequestDto,id));
    }

    //Not used because if remove authors, it could let books without any author (incorrect behavior).
    /*@DeleteMapping(value = "/authors/{id}")
    public ResponseEntity<Object> removeAuthor(@PathVariable Long id){
        authorService.removeAuthor(id);
        return ResponseEntity.ok("Removed Author");
    }*/

}
