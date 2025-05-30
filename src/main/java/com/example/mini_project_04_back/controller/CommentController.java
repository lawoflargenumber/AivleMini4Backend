package com.example.mini_project_04_back.controller;

import com.example.mini_project_04_back.dto.BookDTO;
import com.example.mini_project_04_back.dto.CommentDTO;
import com.example.mini_project_04_back.exception.ResourceNotFoundException;
import com.example.mini_project_04_back.service.BookServiceImpl;
import com.example.mini_project_04_back.service.CommentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentServiceImpl commentService;

    @PostMapping("/books/{id}/comments")
    public ResponseEntity<?> createComment(@RequestBody CommentDTO.CommentCreateRequest commentCreateRequest, @PathVariable Long id) {
        try {
            CommentDTO.CommentDetailedResponse comment = commentService.createComment(id, commentCreateRequest);
            return new ResponseEntity<CommentDTO.CommentDetailedResponse>(comment, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/books/{id}/comments")
    public ResponseEntity<?> getAllComments(@PathVariable Long id) {
        try {
            List<CommentDTO.CommentDetailedResponse> list = commentService.getAllComments(id);
            return new ResponseEntity<List<CommentDTO.CommentDetailedResponse>>(list, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/comments/{id}")
    public ResponseEntity<?> updateComment(@RequestBody CommentDTO.CommentUpdateRequest commentUpdateRequest, @PathVariable Long id) {
        try {
            CommentDTO.CommentDetailedResponse comment = commentService.updateComment(id, commentUpdateRequest);
            return new ResponseEntity<CommentDTO.CommentDetailedResponse>(comment, HttpStatus.CREATED);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id) {
        try {
            commentService.deleteComment(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
