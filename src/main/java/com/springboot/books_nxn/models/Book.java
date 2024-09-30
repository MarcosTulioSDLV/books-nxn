package com.springboot.books_nxn.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_BOOK")
@AllArgsConstructor
@Getter @Setter @ToString
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String title;

    @Column(nullable = false)
    private Integer edition;

    @Column(nullable = false)
    private Integer publicationYear;

    @ManyToMany
    @JoinTable(name = "TB_BOOK_AUTHOR",
    joinColumns = @JoinColumn(name = "book_id"),
    inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors= new ArrayList<>();

    public Book(){
    }

}
