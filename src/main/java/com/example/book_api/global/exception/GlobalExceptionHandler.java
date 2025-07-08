package com.example.book_api.global.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 예살치 못한 전체 예외 (최후의 보루)
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ApiResponse<Void>> handleGenericException() {
//        String message = "서버 내부 오류가 발생했습니다.";
//        return ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, message);
//    }
}
