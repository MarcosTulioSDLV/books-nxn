package com.springboot.books_nxn.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_AUTHOR")
@AllArgsConstructor
@Getter @Setter @ToString(exclude = "books")
@EqualsAndHashCode(exclude = "books")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String fullName;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private LocalDate birthDate;

    @JsonIgnore
    @ManyToMany(mappedBy = "authors"/*,cascade = CascadeType.REMOVE*/)
    private List<Book> books= new ArrayList<>();

    public Author(){
    }

    public Integer getAge() {
        return calculateAge();
    }

    private Integer calculateAge() {
        return LocalDate.now().getYear()-birthDate.getYear();
    }

}
