package com.example.book_api.domain.log.entity;

import com.example.book_api.domain.log.enums.ActivityType;
import com.example.book_api.domain.log.enums.RequestMethod;
import com.example.book_api.domain.log.enums.TargetType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "logs")
@ToString
@NoArgsConstructor
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Enumerated(value = EnumType.STRING)
    private TargetType targetType;

    private Long targetId;

    @Enumerated(value = EnumType.STRING)
    private RequestMethod requestMethod;

    private String requestUri;

    private ActivityType activityType;

    private int statusCode;

    private String message;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;


    // 가독성과 유연한 객체 생성을 위해 Builder 어노테이션 사용
    @Builder
    public Log(
            Long userId,
            TargetType targetType,
            Long targetId,
            RequestMethod requestMethod,
            String requestUri,
            ActivityType activityType,
            int statusCode,
            String message
    ) {
        this.userId = userId;
        this.targetType = targetType;
        this.targetId = targetId;
        this.requestMethod = requestMethod;
        this.requestUri = requestUri;
        this.activityType = activityType;
        this.statusCode = statusCode;
        this.message = message;
        this.createdAt = LocalDateTime.now();
    }
}
