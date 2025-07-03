package com.com.kba.knowledgebase.dto;

import java.time.LocalDateTime;

public class CommentDto {
    private Long id;
    private String content;
    private String commenter;
    private LocalDateTime createdAt;

    public CommentDto() {}

    public CommentDto(Long id, String content, String commenter, LocalDateTime createdAt) {
        this.id = id;
        this.content = content;
        this.commenter = commenter;
        this.createdAt = createdAt;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getCommenter() { return commenter; }
    public void setCommenter(String commenter) { this.commenter = commenter; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
