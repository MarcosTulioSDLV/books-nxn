package com.springboot.books_nxn.exceptions;

public class BookNotFoundException extends RuntimeException{

    public BookNotFoundException(){
    }

    public BookNotFoundException(String message){
        super(message);
    }
}
