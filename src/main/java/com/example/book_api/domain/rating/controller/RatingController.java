package com.example.book_api.domain.rating.controller;

import com.example.book_api.domain.rating.dto.request.RatingRequestDto;
import com.example.book_api.domain.rating.dto.response.AverageRatingResponse;
import com.example.book_api.domain.rating.dto.response.MyRatingResponse;
import com.example.book_api.domain.rating.dto.response.RatingDistributionResponse;
import com.example.book_api.domain.rating.dto.response.TopRatedBookResponse;
import com.example.book_api.domain.rating.service.RatingService;
import com.example.book_api.domain.auth.annotation.Auth;
import com.example.book_api.domain.auth.dto.AuthUser;
import com.example.book_api.global.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    @PostMapping("/books/{bookId}/ratings")
    public ResponseEntity<ApiResponse<Object>> createRating(@PathVariable Long bookId,
                                                            @Valid @RequestBody RatingRequestDto request,
                                                            @AuthenticationPrincipal AuthUser authUserDto) {
        ratingService.createRating(bookId, request, authUserDto.getId());
        return ApiResponse.success(HttpStatus.CREATED, "평점 등록이 완료되었습니다.", null);
    }

    @PatchMapping("/books/{bookId}/ratings")
    public ResponseEntity<ApiResponse<Object>> updateRating(@PathVariable Long bookId,
                             @Valid @RequestBody RatingRequestDto request,
                             @AuthenticationPrincipal AuthUser authUserDto) {
        ratingService.updateRating(bookId, request, authUserDto.getId());
        return ApiResponse.success(HttpStatus.OK, "평점 수정이 완료되었습니다.", null);
    }

    @DeleteMapping("/books/{bookId}/ratings")
    public ResponseEntity<ApiResponse<Object>> deleteRating(@PathVariable Long bookId,
                                                            @AuthenticationPrincipal AuthUser authUserDto) {
        ratingService.deleteRating(bookId, authUserDto.getId());
        return ApiResponse.success(HttpStatus.OK, "평점 삭제가 완료되었습니다.", null);
    }

    //내가 남긴 평점 조회
    @GetMapping("/books/{bookId}/ratings/me")
    public ResponseEntity<ApiResponse<MyRatingResponse>> getMyRating(@PathVariable Long bookId,
                                                                     @AuthenticationPrincipal AuthUser authUserDto) {
        MyRatingResponse response = ratingService.getMyRating(bookId, authUserDto.getId());
        return ApiResponse.success(HttpStatus.OK, "내 평점 조회 성공", response);
    }

    //책 평점 평균 조회
    @GetMapping("/books/{bookId}/ratings")
    public ResponseEntity<ApiResponse<AverageRatingResponse>> getAverageRating(@PathVariable Long bookId) {
        AverageRatingResponse response = ratingService.getAverageRating(bookId);
        return ApiResponse.success(HttpStatus.OK, "책 평점 평균 조회 성공", response);
    }

    //책 평점 분포 조회
    @GetMapping("/books/{bookId}/ratings/distribution")
    public ResponseEntity<ApiResponse<List<RatingDistributionResponse>>> getDistribution(@PathVariable Long bookId) {
        List<RatingDistributionResponse> response = ratingService.getRatingDistribution(bookId);
        return ApiResponse.success(HttpStatus.OK, "책 평점 분포 조회 성공", response);
    }

    //평점 높은 순 top 10
    @GetMapping("/books/top/ratings")
    public ResponseEntity<ApiResponse<List<TopRatedBookResponse>>> getTopRatedBooks() {
        List<TopRatedBookResponse> response = ratingService.getTop10RatedBooks();
        return ApiResponse.success(HttpStatus.OK, "Top 10 평점 높은 책 조회 성공", response);
    }

}
