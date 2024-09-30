package com.springboot.books_nxn.dtos;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class AuthorResponseDto {

    private Long id;

    private String fullName;

    private String country;

    private String city;

    private LocalDate birthDate;

    private Integer age;

}
