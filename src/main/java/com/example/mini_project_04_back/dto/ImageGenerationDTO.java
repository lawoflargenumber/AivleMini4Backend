package com.example.mini_project_04_back.dto;


import lombok.*;

public class ImageGenerationDTO {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ImageGenerationRequest {
        private String prompt;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ImageGenerationResponse {
        private String imageUrl;
    }
}
