package com.example.mini_project_04_back.service;

import com.example.mini_project_04_back.domain.Book;
import com.example.mini_project_04_back.dto.BookDTO;
import com.example.mini_project_04_back.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    @Override
    public BookDTO.BookDetailedResponse createBook(BookDTO.BookCreateRequest requestDTO) {
        Book book = requestDTO.toEntity();
        Book savedBook = bookRepository.save(book);
        return BookDTO.BookDetailedResponse.fromEntity(savedBook);
    }

    @Override
    public List<BookDTO.BookSimpleResponse> getAllBooks() {
        return bookRepository.findAll()
                .stream().map(BookDTO.BookSimpleResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO.BookDetailedResponse getBookById(Long id) {
        return BookDTO.BookDetailedResponse.fromEntity(
                bookRepository.findById(id).orElseThrow(
                        () -> new EntityNotFoundException("없는 id 입니다.")
                )
        );
    }

    @Override
    public BookDTO.BookDetailedResponse updateBook(Long id, BookDTO.BookUpdateRequest requestDTO) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("없는 id 입니다.")
        );

        book.setTitle(requestDTO.getTitle());
        book.setContent(requestDTO.getContent());
        book.setCoverImageUrl(requestDTO.getCoverImageUrl());

        Book savedBook = bookRepository.save(book);
        return BookDTO.BookDetailedResponse.fromEntity(savedBook);
    }

    @Override
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new EntityNotFoundException("없는 id 입니다.");
        }

        bookRepository.deleteById(id);
    }
}