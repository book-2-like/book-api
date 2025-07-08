package com.example.book_api.domain.comment.controller;

import com.example.book_api.domain.comment.dto.CommentRequestDto;
import com.example.book_api.domain.comment.dto.CommentResponseDto;
import com.example.book_api.domain.comment.service.CommentService;
import com.example.book_api.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    // TODO 기능 구현 모두 완료 후, 요구사항 확인 후 수정
    // TODO API, Entity(varchar)와 일치하는지 확인 수정
    // TODO Validated 예외처리
    // 썌
    // create
    @PostMapping("/books/{bookId}/comments")
    public ResponseEntity<ApiResponse<CommentResponseDto>> createComment(@PathVariable Long bookId,
                                                     @RequestBody @Validated CommentRequestDto request) {
        // TODO 인가 처리
        Long userId = 1L;

        CommentResponseDto response = commentService.create(userId, bookId, request);

        return ApiResponse.success(HttpStatus.CREATED, userId + "님 댓글이 생성되었습니다.", response);
    }

    // read
    @GetMapping("/api/v1/books/{bookId}/comments")
    public ResponseEntity<ApiResponse<List<CommentResponseDto>>> getComments(@PathVariable Long bookId) {

        // TODO 인가 처리
        Long userId = 1L;

        List<CommentResponseDto> responses = commentService.get(bookId);

        return ApiResponse.success(HttpStatus.OK, userId + "님 댓글이 조회되었습니다.", responses);
    }

    // update

    // delete
}
