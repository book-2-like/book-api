package com.example.book_api.domain.log.dto;

import lombok.Getter;

@Getter
public class LogRequestDto {
    private Long userId;
    private String targetType;
    private String startAt;
    private String endAt;

    public LogRequestDto(Long userId, String targetType, String startAt, String endAt) {
        this.userId = userId;
        this.targetType = targetType;
        this.startAt = startAt;
        this.endAt = endAt;
    }
}
