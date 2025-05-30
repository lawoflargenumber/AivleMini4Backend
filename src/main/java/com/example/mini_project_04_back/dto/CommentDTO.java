package com.example.mini_project_04_back.dto;

import com.example.mini_project_04_back.domain.Book;
import com.example.mini_project_04_back.domain.Comment;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

public class CommentDTO {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommentCreateRequest {

        @NotBlank(message = "내용은 필수 입력 값입니다.")
        private String content;

        public Comment toEntity() {
            return Comment.builder()
                    .content(this.content)
                    .build();
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommentUpdateRequest {

        @NotBlank(message = "내용은 필수 입력 값입니다.")
        private String content;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CommentDetailedResponse {
        private Long id;
        private Long bookId;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public static CommentDetailedResponse fromEntity(Comment comment) {
            return CommentDetailedResponse.builder()
                    .id(comment.getId())
                    .bookId(comment.getBook().getId())
                    .content(comment.getContent())
                    .createdAt(comment.getCreatedAt())
                    .updatedAt(comment.getUpdatedAt())
                    .build();
        }
    }
}
