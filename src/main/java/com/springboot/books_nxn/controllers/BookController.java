package com.springboot.books_nxn.controllers;

import com.springboot.books_nxn.dtos.BookRequestDto;
import com.springboot.books_nxn.dtos.BookResponseDto;
import com.springboot.books_nxn.dtos.UpdateBookRequestDto;
import com.springboot.books_nxn.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping(value = "/books")
    public ResponseEntity<Page<BookResponseDto>> getAllBooks(@PageableDefault(size = 5) Pageable pageable){
        return ResponseEntity.ok(bookService.getAllBooks(pageable));
    }

    @GetMapping(value = "/books/{id}")
    public ResponseEntity<BookResponseDto> getBookById(@PathVariable Long id){
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PostMapping(value = "/books")
    public ResponseEntity<BookResponseDto> addBook(@RequestBody @Valid BookRequestDto bookRequestDto){
        return new ResponseEntity<>(bookService.addBook(bookRequestDto),HttpStatus.CREATED);
    }

    @PutMapping(value = "/books/{id}")
    public ResponseEntity<BookResponseDto> updateBook(@RequestBody @Valid UpdateBookRequestDto updateBookRequestDto,
                                                      @PathVariable Long id){
        return ResponseEntity.ok(bookService.updateBook(updateBookRequestDto,id));
    }

    @DeleteMapping(value = "/books/{id}")
    public ResponseEntity<Object> removeBook(@PathVariable Long id){
        bookService.removeBook(id);
        return ResponseEntity.ok("Book removed successfully!");
    }

}
