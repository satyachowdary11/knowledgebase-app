package com.com.kba.knowledgebase.service;

import com.com.kba.knowledgebase.dto.ArticleDto;
import com.com.kba.knowledgebase.dto.PagedResponse;
import com.com.kba.knowledgebase.entity.Article;
import com.com.kba.knowledgebase.entity.User;
import com.com.kba.knowledgebase.exception.ResourceNotFoundException;
import com.com.kba.knowledgebase.repository.ArticleRepository;
import com.com.kba.knowledgebase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;

    // ✅ GET PAGINATED ARTICLES
    public PagedResponse<ArticleDto> getPaginatedArticles(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Article> articlePage = articleRepository.findAll(pageable);

        List<ArticleDto> content = articlePage.getContent().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

        return new PagedResponse<>(
                content,
                articlePage.getNumber(),
                articlePage.getSize(),
                articlePage.getTotalElements(),
                articlePage.getTotalPages(),
                articlePage.isLast()
        );
    }

    // ✅ SEARCH ARTICLES BY KEYWORD
    public PagedResponse<ArticleDto> searchArticles(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Article> articlePage = articleRepository.findByTitleContainingIgnoreCase(keyword, pageable);

        List<ArticleDto> content = articlePage.getContent().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

        return new PagedResponse<>(
                content,
                articlePage.getNumber(),
                articlePage.getSize(),
                articlePage.getTotalElements(),
                articlePage.getTotalPages(),
                articlePage.isLast()
        );
    }

    // ✅ GET ARTICLES BY AUTHOR
    public PagedResponse<ArticleDto> getArticlesByAuthor(String username, int page, int size) {
        User author = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));

        Pageable pageable = PageRequest.of(page, size);
        Page<Article> articlePage = articleRepository.findByAuthor(author, pageable);

        List<ArticleDto> content = articlePage.getContent().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

        return new PagedResponse<>(
                content,
                articlePage.getNumber(),
                articlePage.getSize(),
                articlePage.getTotalElements(),
                articlePage.getTotalPages(),
                articlePage.isLast()
        );
    }


    // ✅ MAPPING METHOD
    private ArticleDto mapToDto(Article article) {
        return new ArticleDto(
                article.getId(),
                article.getTitle(),
                article.getContent(),
                article.getAuthor().getUsername(),
                article.getViews(),
                article.getCreatedAt()
        );
    }
    
    // crud methods
    public void createArticle(ArticleDto dto, String username) {
        User author = userRepository.findByUsername(username)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Article article = new Article();
        article.setTitle(dto.getTitle());
        article.setContent(dto.getContent());
        article.setAuthor(author);
        article.setCreatedAt(LocalDateTime.now());
        article.setUpdatedAt(LocalDateTime.now());
        article.setViews(0);

        articleRepository.save(article);
    }

    public void updateArticle(Long id, ArticleDto dto) {
        Article article = articleRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Article not found"));

        article.setTitle(dto.getTitle());
        article.setContent(dto.getContent());
        article.setUpdatedAt(LocalDateTime.now());

        articleRepository.save(article);
    }

    public void deleteArticle(Long id) {
        if (!articleRepository.existsById(id)) {
            throw new ResourceNotFoundException("Article not found");
        }
        articleRepository.deleteById(id);
    }
//by category
    public PagedResponse<ArticleDto> getArticlesByCategory(String categoryName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Article> articlePage = articleRepository.findByCategory_Name(categoryName, pageable);

        List<ArticleDto> content = articlePage.getContent().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

        return new PagedResponse<>(
                content,
                articlePage.getNumber(),
                articlePage.getSize(),
                articlePage.getTotalElements(),
                articlePage.getTotalPages(),
                articlePage.isLast()
        );
    }

  
}
