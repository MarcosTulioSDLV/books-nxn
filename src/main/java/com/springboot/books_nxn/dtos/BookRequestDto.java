package com.springboot.books_nxn.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class BookRequestDto {

    @NotBlank
    private String title;

    @PositiveOrZero @NotNull
    private Integer edition;

    @PositiveOrZero @NotNull
    private Integer publicationYear;

    @NotEmpty
    private List<@Positive Long> authorIds= new ArrayList<>();

}
