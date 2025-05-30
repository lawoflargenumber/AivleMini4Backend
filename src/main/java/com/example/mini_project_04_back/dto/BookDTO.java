package com.example.mini_project_04_back.dto;

import com.example.mini_project_04_back.domain.Book;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class BookDTO {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BookCreateRequest {
        private String title;
        private String content;
        private String coverImageUrl;

        public Book toEntity() {
            return Book.builder()
                    .title(this.title)
                    .content(this.content)
                    .coverImageUrl(this.coverImageUrl)
                    .build();
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BookUpdateRequest {
        private String title;
        private String content;
        private String coverImageUrl;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BookSimpleResponse {
        private Long id;
        private String title;
        private String coverImageUrl;
        private LocalDateTime createdAt;

        public static BookSimpleResponse fromEntity(Book book) {
            return BookSimpleResponse.builder()
                    .id(book.getId())
                    .title(book.getTitle())
                    .coverImageUrl(book.getCoverImageUrl())
                    .createdAt(book.getCreatedAt())
                    .build();
        }

        public static List<BookSimpleResponse> fromEntityList(List<Book> books) {
            return books.stream()
                    .map(BookSimpleResponse::fromEntity)
                    .collect(Collectors.toList());
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BookDetailedResponse {
        private Long id;
        private String title;
        private String content;
        private String coverImageUrl;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public static BookDetailedResponse fromEntity(Book book) {
            return BookDetailedResponse.builder()
                    .id(book.getId())
                    .title(book.getTitle())
                    .content(book.getContent())
                    .coverImageUrl(book.getCoverImageUrl())
                    .createdAt(book.getCreatedAt())
                    .updatedAt(book.getUpdatedAt())
                    .build();
        }
    }


}
