package com.example.mini_project_04_back.service;

import com.example.mini_project_04_back.domain.Book;
import com.example.mini_project_04_back.dto.BookDTO;
import com.example.mini_project_04_back.exception.ResourceNotFoundException;
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
        Book entityBook = book.toEntity();
        Book saveBook = bookRepository.save(entityBook);
        return BookDTO.BookDetailedResponse.fromEntity(saveBook);
    }

    @Override
    public List<BookDTO.BookSimpleResponse> getAllBooks() {
        return BookDTO.BookSimpleResponse.fromEntityList(bookRepository.findAll());
    }

    @Override
    public BookDTO.BookSimpleResponse getBookById(Long id) {
        return BookDTO.BookSimpleResponse.fromEntity(bookRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("해당 아이디를 갖는 책")
        ));
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
