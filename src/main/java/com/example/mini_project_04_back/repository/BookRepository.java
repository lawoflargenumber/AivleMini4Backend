package com.example.mini_project_04_back.repository;

import com.example.mini_project_04_back.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleContaining(String title);

    @Query("SELECT b FROM Book b LEFT JOIN FETCH b.comments WHERE b.id = :id")
    Optional<Book> findByIdWithComments(@Param("id") Long id);
}