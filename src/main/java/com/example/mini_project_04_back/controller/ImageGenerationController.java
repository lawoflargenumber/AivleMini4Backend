package com.example.mini_project_04_back.controller;

import com.example.mini_project_04_back.dto.ImageGenerationDTO;
import com.example.mini_project_04_back.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books/{id}/generate-cover")
@RequiredArgsConstructor
public class ImageGenerationController {

    private final OpenAiService openAiService;

    @PostMapping
    public ResponseEntity<ImageGenerationDTO.ImageGenerationResponse> generate(@RequestBody ImageGenerationDTO.ImageGenerationRequest request) {
        String url = openAiService.generateImage(request.getPrompt());
        ImageGenerationDTO.ImageGenerationResponse response = new ImageGenerationDTO.ImageGenerationResponse();
        response.setImageUrl(url);
        return ResponseEntity.ok(response);
    }
}
