package com.example.mini_project_04_back.service;

import com.example.mini_project_04_back.domain.Book;
import com.example.mini_project_04_back.domain.Comment;
import com.example.mini_project_04_back.dto.CommentDTO;
import com.example.mini_project_04_back.repository.BookRepository;
import com.example.mini_project_04_back.repository.CommentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;

    @Override
    public CommentDTO.CommentDetailedResponse createComment(Long bookId, CommentDTO.CommentCreateRequest requestDTO) {
        Book book = bookRepository.findById(bookId).orElseThrow(
                        () -> new EntityNotFoundException("없는 id 입니다.")
                );

        Comment comment = requestDTO.toEntity();

        book.addComment(comment);

        Comment savedComment = commentRepository.save(comment);
        return CommentDTO.CommentDetailedResponse.fromEntity(savedComment);
    }

    @Override
    public List<CommentDTO.CommentDetailedResponse> getAllComments(Long bookId) {
        return commentRepository
                .findByBookId(bookId)
                .stream()
                .map(CommentDTO.CommentDetailedResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public CommentDTO.CommentDetailedResponse updateComment(Long id, CommentDTO.CommentUpdateRequest requestDTO) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("없는 댓글 id 입니다."));

        comment.setContent(requestDTO.getContent());
        Comment savedComment = commentRepository.save(comment);

        return CommentDTO.CommentDetailedResponse.fromEntity(savedComment);
    }

    @Override
    public void deleteComment(Long id) {
        if (commentRepository.existsById(id)) {
            throw new EntityNotFoundException("없는 댓글 id 입니다.");
        }

        commentRepository.deleteById(id);
    }
}
