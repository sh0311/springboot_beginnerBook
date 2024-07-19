package com.example.bookFirstProject.repository;

import com.example.bookFirstProject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByArticleId(Long ArticleId);
    List<Comment> findByNickname(String Nickname);
}
