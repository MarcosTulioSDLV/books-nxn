package com.springboot.books_nxn.exceptions;

public class AuthorNotFoundException extends RuntimeException{

    public AuthorNotFoundException(){
    }

    public AuthorNotFoundException(String message){
        super(message);
    }

}
