package com.springboot.books_nxn.mappers;

import com.springboot.books_nxn.dtos.BookRequestDto;
import com.springboot.books_nxn.dtos.BookResponseDto;
import com.springboot.books_nxn.dtos.UpdateBookRequestDto;
import com.springboot.books_nxn.models.Book;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public BookMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public BookResponseDto toBookResponseDto(Book book){
        return modelMapper.map(book,BookResponseDto.class);
    }


    public Book toBook(BookRequestDto bookRequestDto){
        return modelMapper.map(bookRequestDto,Book.class);
    }

    public Book toBook(UpdateBookRequestDto updateBookRequestDto){
        return modelMapper.map(updateBookRequestDto,Book.class);
    }


}
