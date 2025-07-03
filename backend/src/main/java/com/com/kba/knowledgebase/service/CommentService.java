package com.com.kba.knowledgebase.service;

import com.com.kba.knowledgebase.dto.CommentDto;
import com.com.kba.knowledgebase.entity.Article;
import com.com.kba.knowledgebase.entity.Comment;
import com.com.kba.knowledgebase.repository.ArticleRepository;
import com.com.kba.knowledgebase.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;

    public List<CommentDto> getCommentsForArticle(Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new RuntimeException("Article not found"));

        return commentRepository.findByArticle(article)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private CommentDto mapToDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getContent(),
                comment.getUser().getUsername(),
                comment.getCreatedAt()
        );
    }
}
