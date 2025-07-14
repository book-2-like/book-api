package com.example.book_api.domain.log.controller;

import com.example.book_api.domain.log.dto.LogResponseDto;
import com.example.book_api.domain.log.service.LogService;
import com.example.book_api.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 로그 기능의 HTTP 요청을 처리하는 컨트롤러
 *
 * @author 이현하
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class LogController {

    private final LogService logService;

    /**
     * 저장된 로그를 조회하는 기능
     *
     * @param page 페이지 번호
     * @param size 한 페이지 내 요소 수
     * @param userId 유저 id 검색
     * @param targetType 도메인 타입 검색
     * @param startAt 시작일 검색
     * @param endAt 종료일 검색
     * @return 페이징 처리된 응답 Dto
     */
    @GetMapping("/logs")
    public ResponseEntity<ApiResponse<Page<LogResponseDto>>> getLogs(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) String targetType,
            @RequestParam(required = false) String startAt,
            @RequestParam(required = false) String endAt
    ) {

        Page<LogResponseDto> logs = logService.getLogs(page, size, userId, targetType, startAt, endAt);

        return ApiResponse.success(
                HttpStatus.OK, "로그 조회가 완료되었습니다.", logs);
    }
}
