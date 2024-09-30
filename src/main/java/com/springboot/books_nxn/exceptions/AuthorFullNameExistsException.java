package com.springboot.books_nxn.exceptions;

public class AuthorFullNameExistsException extends RuntimeException{

    public AuthorFullNameExistsException(){
    }

    public AuthorFullNameExistsException(String message){
        super(message);
    }

}
