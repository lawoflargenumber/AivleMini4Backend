package com.example.mini_project_04_back.service;

import com.example.mini_project_04_back.domain.Book;
import com.example.mini_project_04_back.domain.Comment;
import com.example.mini_project_04_back.dto.CommentDTO;
import com.example.mini_project_04_back.repository.BookRepository;
import com.example.mini_project_04_back.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;

    @Override
    @Transactional
    public CommentDTO.CommentDetailedResponse createComment(Long id, CommentDTO.CommentCreateRequest comment) {
        Book book = bookRepository.findById(id).orElseThrow();
        Comment save = comment.toEntity();
        save.setBook(book);
        Comment newComment = commentRepository.save(save);
        return CommentDTO.CommentDetailedResponse.fromEntity(newComment);
    }

    @Override
    public List<CommentDTO.CommentDetailedResponse> getAllComments(Long id) {
        List<Comment> list = commentRepository.findByBookId(id);

        return list.stream()
                .map(CommentDTO.CommentDetailedResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CommentDTO.CommentDetailedResponse updateComment(Long id, CommentDTO.CommentUpdateRequest comment) {
        Comment info = commentRepository.findById(id).orElseThrow();

        info.setContent(comment.getContent());

        return CommentDTO.CommentDetailedResponse.fromEntity(info);
    }

    @Override
    @Transactional
    public void deleteComment(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow();
        commentRepository.delete(comment);
    }
}
