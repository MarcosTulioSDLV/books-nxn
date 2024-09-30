package com.springboot.books_nxn.dtos;

import com.springboot.books_nxn.models.Author;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class BookResponseDto {

    private Long id;

    private String title;

    private Integer edition;

    private Integer publicationYear;

    private List<Author> authors= new ArrayList<>();

}
