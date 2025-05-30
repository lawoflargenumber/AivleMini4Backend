package com.example.mini_project_04_back.service;

import com.example.mini_project_04_back.dto.CommentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    @Override
    public CommentDTO.CommentDetailedResponse createComment(Long id, CommentDTO.CommentCreateRequest comment) {
        return null;
    }

    @Override
    public List<CommentDTO.CommentDetailedResponse> getAllComments(Long id) {
        return List.of();
    }

    @Override
    public CommentDTO.CommentDetailedResponse updateComment(Long id, CommentDTO.CommentUpdateRequest comment) {
        return null;
    }

    @Override
    public void deleteComment(Long id) {

    }
}
