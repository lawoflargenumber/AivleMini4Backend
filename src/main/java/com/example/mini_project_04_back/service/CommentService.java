package com.example.mini_project_04_back.service;

import com.example.mini_project_04_back.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    CommentDTO.CommentDetailedResponse createComment(Long id, CommentDTO.CommentCreateRequest comment);
    List<CommentDTO.CommentDetailedResponse> getAllComments(Long id);
    CommentDTO.CommentDetailedResponse updateComment(Long id, CommentDTO.CommentUpdateRequest comment);
    void deleteComment(Long id);
}
