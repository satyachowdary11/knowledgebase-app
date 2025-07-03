package com.com.kba.knowledgebase.repository;

import com.com.kba.knowledgebase.entity.Article;
import com.com.kba.knowledgebase.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Page<Article> findByAuthor(User author, Pageable pageable);
    Page<Article> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);
    Page<Article> findByCategory_Name(String categoryName, Pageable pageable);

}
