package com.example.book_api.domain.book.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.awt.print.Book;
import java.time.LocalDateTime;

@Getter
@Entity
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "contnet", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 작성자

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book; // 책

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public Comment(String content, User user, Book book) {
        this.content = content;
        this.user = user;
        this.book = book;
    }

    public void delete() {
        this.deletedAt = LocalDateTime.now();
    }
}
