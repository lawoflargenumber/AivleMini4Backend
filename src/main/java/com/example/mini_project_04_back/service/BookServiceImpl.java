package com.example.mini_project_04_back.service;

import com.example.mini_project_04_back.domain.Book;
import com.example.mini_project_04_back.dto.BookDTO;
import com.example.mini_project_04_back.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    @Override
    @Transactional
    public BookDTO.BookDetailedResponse createBook(BookDTO.BookCreateRequest book) {
        Book newBook = bookRepository.save(book.toEntity());
        return BookDTO.BookDetailedResponse.fromEntity(newBook);
    }

    @Override
    public List<BookDTO.BookSimpleResponse> getAllBooks() {
        List<Book> list = bookRepository.findAll();

        return list.stream()
                .map(BookDTO.BookSimpleResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO.BookSimpleResponse getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow();
        return BookDTO.BookSimpleResponse.fromEntity(book);
    }

    @Override
    @Transactional
    public BookDTO.BookDetailedResponse updateBook(Long id, BookDTO.BookUpdateRequest book) {
        Book info = bookRepository.findById(id).orElseThrow();

        info.setTitle(book.getTitle());
        info.setContent(book.getContent());
        info.setCoverImageUrl(book.getCoverImageUrl());

        return BookDTO.BookDetailedResponse.fromEntity(info);
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow();
        bookRepository.delete(book);
    }
}
