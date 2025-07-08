package com.example.book_api.domain.comment.dto;

import lombok.Getter;

@Getter
public class CommentResponseDto {

    private String content;
    private Long userId;
    private Long bookId;

    public CommentResponseDto(String content, Long userId, Long bookId) {
        this.content = content;
        this.userId = userId;
        this.bookId = bookId;
    }
}
