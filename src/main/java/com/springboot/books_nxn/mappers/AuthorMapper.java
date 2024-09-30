package com.springboot.books_nxn.mappers;

import com.springboot.books_nxn.dtos.AuthorRequestDto;
import com.springboot.books_nxn.dtos.AuthorResponseDto;
import com.springboot.books_nxn.models.Author;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public AuthorMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public AuthorResponseDto toAuthorResponseDto(Author author){
        return modelMapper.map(author, AuthorResponseDto.class);
    }

    public Author toAuthor(AuthorRequestDto authorRequestDto){
        return modelMapper.map(authorRequestDto,Author.class);
    }

}
