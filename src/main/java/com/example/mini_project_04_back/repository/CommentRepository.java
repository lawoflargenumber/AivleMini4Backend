package com.example.mini_project_04_back.repository;

import com.example.mini_project_04_back.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
