package com.springboot.books_nxn.exceptions;

public class BookTitleExistsException extends RuntimeException{

    public BookTitleExistsException(){
    }

    public BookTitleExistsException(String message){
        super(message);
    }
}
