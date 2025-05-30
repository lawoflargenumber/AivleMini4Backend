package com.example.mini_project_04_back.service;

import com.example.mini_project_04_back.dto.BookDTO;
import com.example.mini_project_04_back.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    @Override
    public BookDTO.BookDetailedResponse createBook(BookDTO.BookCreateRequest book) {
        return null;
    }

    @Override
    public List<BookDTO.BookSimpleResponse> getAllBooks() {
        return List.of();
    }

    @Override
    public BookDTO.BookSimpleResponse getBookById(Long id) {
        return null;
    }

    @Override
    public BookDTO.BookDetailedResponse updateBook(Long id, BookDTO.BookUpdateRequest book) {
        //도서가 존재하지 않는 경우 예외처리
        return null;
    }

    @Override
    public void deleteBook(Long id) {

    }
}
