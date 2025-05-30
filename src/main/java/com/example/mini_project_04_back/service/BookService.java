package com.example.mini_project_04_back.service;

import com.example.mini_project_04_back.dto.BookDTO;

import java.util.List;

public interface BookService {
    BookDTO.BookDetailedResponse createBook(BookDTO.BookCreateRequest book);
    List<BookDTO.BookSimpleResponse> getAllBooks();
    BookDTO.BookSimpleResponse getBookById(Long id);
    BookDTO.BookDetailedResponse updateBook(Long id, BookDTO.BookUpdateRequest book);
    void deleteBook(Long id);
}
