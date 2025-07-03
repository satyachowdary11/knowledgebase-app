package com.com.kba.knowledgebase.dto;

import java.time.LocalDateTime;

public class ArticleDto {
    private Long id;
    private String title;
    private String content;
    private String authorName;
    private int views;
    private LocalDateTime createdAt;

    public ArticleDto() {}

    public ArticleDto(Long id, String title, String content, String authorName,
                      int views, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorName = authorName;
        this.views = views;
        this.createdAt = createdAt;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getAuthorName() { return authorName; }
    public void setAuthorName(String authorName) { this.authorName = authorName; }

    public int getViews() { return views; }
    public void setViews(int views) { this.views = views; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
