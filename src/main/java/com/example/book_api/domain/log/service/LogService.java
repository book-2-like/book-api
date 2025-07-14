package com.example.book_api.domain.log.service;

import com.example.book_api.domain.log.dto.LogRequestDto;
import com.example.book_api.domain.log.dto.LogResponseDto;
import com.example.book_api.domain.log.entity.Log;
import com.example.book_api.domain.log.repository.LogRepository;
import com.example.book_api.global.dto.PagedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 로그 저장, 조회 기능 클래스
 *
 * @author 이현하
 */
@RequiredArgsConstructor
@Service
public class LogService {

    private final LogRepository logRepository;

    /**
     * 로그를 저장하는 기능
     *
     * @param log AOP를 통해 빌드한 로그 객체
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveLog(Log log){
        logRepository.save(log);
    }


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
    public PagedResponse<LogResponseDto> getLogs(int page, int size,
                                                 Long userId, String targetType, String startAt, String endAt) {
        LogRequestDto requestDto = new LogRequestDto(userId, targetType, startAt, endAt);

        Pageable pageable = PageRequest.of(page-1, size);
        Page<Log> logs = logRepository.findByFilter(requestDto, pageable);

        Page<LogResponseDto> dtoPage = logs.map(log -> new LogResponseDto(
                log.getId(),
                log.getUserId(),
                log.getTargetType(),
                log.getTargetId(),
                log.getRequestMethod(),
                log.getRequestUri(),
                log.getActivityType(),
                log.getStatusCode(),
                log.getMessage(),
                log.getCreatedAt()
        ));

        return PagedResponse.toPagedResponse(dtoPage);
    }
}