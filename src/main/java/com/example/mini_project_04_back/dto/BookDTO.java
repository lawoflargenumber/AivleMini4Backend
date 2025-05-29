package com.example.mini_project_04_back.dto;

import lombok.*;

import java.time.LocalDateTime;

public class BookDTO {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BookCreateRequest {
        private String title;
        private String content;
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
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BookDetailedResponse {
        private Long id;
        private String title;
        private String coverImageUrl;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }


}
