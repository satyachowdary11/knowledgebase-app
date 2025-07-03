package com.com.kba.knowledgebase.controller;

import com.com.kba.knowledgebase.dto.ArticleDto;
import com.com.kba.knowledgebase.dto.PagedResponse;

import com.com.kba.knowledgebase.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    // Pagination + Sorting
    @GetMapping
    public PagedResponse<ArticleDto> getArticles(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size,
        @RequestParam(defaultValue = "createdAt") String sortBy,
        @RequestParam(defaultValue = "desc") String sortDir
    ) {
        return articleService.getPaginatedArticles(page, size, sortBy, sortDir);
    }


    // Search
    @GetMapping("/search")
    public PagedResponse<ArticleDto> searchArticles(
        @RequestParam String keyword,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size
    ) {
        return articleService.searchArticles(keyword, page, size);
    }
    
 // CREATE Article (ADMIN or EDITOR)
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'EDITOR')")
    public ResponseEntity<String> createArticle(@RequestBody ArticleDto articleDto,
                                                @RequestParam String username) {
        articleService.createArticle(articleDto, username);
        return ResponseEntity.ok("Article created!");
    }

    // UPDATE Article (ADMIN or the author)
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EDITOR')")
    public ResponseEntity<String> updateArticle(@PathVariable Long id,
                                                @RequestBody ArticleDto articleDto) {
        articleService.updateArticle(id, articleDto);
        return ResponseEntity.ok("Article updated!");
    }

    // DELETE Article (ADMIN only)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return ResponseEntity.ok("Article deleted!");
    }
    
    //by auther
    @GetMapping("/by-author/{username}")
    public PagedResponse<ArticleDto> getArticlesByAuthor(
            @PathVariable String username,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return articleService.getArticlesByAuthor(username, page, size);
    }
    //by category
    @GetMapping("/by-category/{name}")
    public PagedResponse<ArticleDto> getArticlesByCategory(
            @PathVariable String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return articleService.getArticlesByCategory(name, page, size);
    }

}