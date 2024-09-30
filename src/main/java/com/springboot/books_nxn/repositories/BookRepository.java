package com.springboot.books_nxn.repositories;

import com.springboot.books_nxn.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    boolean existsByTitleIgnoreCase(String title);

}
