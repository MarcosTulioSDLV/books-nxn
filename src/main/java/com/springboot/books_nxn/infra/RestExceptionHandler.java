package com.springboot.books_nxn.infra;

import com.springboot.books_nxn.exceptions.AuthorFullNameExistsException;
import com.springboot.books_nxn.exceptions.AuthorNotFoundException;
import com.springboot.books_nxn.exceptions.BookNotFoundException;
import com.springboot.books_nxn.exceptions.BookTitleExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    //For Author class

    @ExceptionHandler(AuthorNotFoundException.class)
    public ResponseEntity<String> handleAuthorNotFoundException(AuthorNotFoundException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuthorFullNameExistsException.class)
    public ResponseEntity<String> handleAuthorFullNameExistsException(AuthorFullNameExistsException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    //-----
    //For Book class

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<String> handleBookNotFoundException(BookNotFoundException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookTitleExistsException.class)
    public ResponseEntity<String> handleBookTitleExistsException(BookTitleExistsException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    //-----

}
