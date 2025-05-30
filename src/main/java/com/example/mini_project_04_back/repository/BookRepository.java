package com.example.mini_project_04_back.repository;

import com.example.mini_project_04_back.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
