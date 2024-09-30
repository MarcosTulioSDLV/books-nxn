package com.springboot.books_nxn.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateBookRequestDto {

    @NotBlank
    private String title;

    @PositiveOrZero
    @NotNull
    private Integer edition;

    @PositiveOrZero @NotNull
    private Integer publicationYear;

}
