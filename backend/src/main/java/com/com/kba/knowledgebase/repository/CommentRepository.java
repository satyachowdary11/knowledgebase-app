package com.com.kba.knowledgebase.repository;

import com.com.kba.knowledgebase.entity.Comment;
import com.com.kba.knowledgebase.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByArticle(Article article);
}
