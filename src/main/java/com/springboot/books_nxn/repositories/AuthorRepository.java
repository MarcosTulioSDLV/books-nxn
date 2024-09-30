package com.springboot.books_nxn.repositories;

import com.springboot.books_nxn.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {

    boolean existsByFullNameIgnoreCase(String fullName);


}
