package com.com.kba.knowledgebase.controller;

import com.com.kba.knowledgebase.dto.CommentDto;
import com.com.kba.knowledgebase.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/article/{articleId}")
    public List<CommentDto> getCommentsByArticle(@PathVariable Long articleId) {
        return commentService.getCommentsForArticle(articleId);
    }
}
