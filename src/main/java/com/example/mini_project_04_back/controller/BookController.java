package com.example.mini_project_04_back.controller;

import com.example.mini_project_04_back.dto.BookDTO;
import com.example.mini_project_04_back.exception.ResourceNotFoundException;
import com.example.mini_project_04_back.service.BookServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookServiceImpl bookService;

    @PostMapping("/books")
    public ResponseEntity<?> createBook(@RequestBody BookDTO.BookCreateRequest bookCreateRequest) {
        try {
            BookDTO.BookDetailedResponse book = bookService.createBook(bookCreateRequest);
            return new ResponseEntity<BookDTO.BookDetailedResponse>(book, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/books")
    public ResponseEntity<?> getAllBooks() {
        try {
            List<BookDTO.BookSimpleResponse> list = bookService.getAllBooks();
            return new ResponseEntity<List<BookDTO.BookSimpleResponse>>(list, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        try {
            BookDTO.BookDetailedResponse book = bookService.getBookById(id);
            return new ResponseEntity<BookDTO.BookDetailedResponse>(book, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/books/{id}")
    public ResponseEntity<?> updateBook(@RequestBody BookDTO.BookUpdateRequest bookUpdateRequest, @PathVariable Long id) {
        try {
            BookDTO.BookDetailedResponse book = bookService.updateBook(id, bookUpdateRequest);
            return new ResponseEntity<BookDTO.BookDetailedResponse>(book, HttpStatus.CREATED);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        try {
            bookService.deleteBook(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
