package com.springboot.books_nxn.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.books_nxn.models.Book;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class AuthorRequestDto {

    @NotBlank
    private String fullName;

    @NotBlank
    private String country;

    @NotBlank
    private String city;

    @NotNull @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

}
