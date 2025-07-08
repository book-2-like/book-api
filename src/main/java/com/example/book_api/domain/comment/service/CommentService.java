package com.example.book_api.domain.comment.service;

import com.example.book_api.domain.book.entity.Book;
import com.example.book_api.domain.book.repository.BookRepository;
import com.example.book_api.domain.comment.dto.CommentRequestDto;
import com.example.book_api.domain.comment.dto.CommentResponseDto;
import com.example.book_api.domain.comment.entity.Comment;
import com.example.book_api.domain.comment.repository.CommentRepository;
import com.example.book_api.domain.user.entity.User;
import com.example.book_api.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    // TODO 서비스로 변경하기
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public CommentResponseDto create(Long userId, Long bookId, CommentRequestDto request) {

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("해당 유저는 존재하지 않습니다."));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("해당 책이 존재하지 않습니다."));

        Comment comment = new Comment(request.getContent(), user, book);
        Comment savedComment = commentRepository.save(comment);

        return new CommentResponseDto(savedComment.getContent(), savedComment.getUser().getId(), savedComment.getBook().getId());
    }

    public List<CommentResponseDto> get(Long bookId) {

        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("해당 책이 존재하지 않습니다."));

        List<Comment> commentList = commentRepository.findAll();

        List<CommentResponseDto> responses = commentList.stream()
                                            .map(comment -> new CommentResponseDto(comment.getContent(), comment.getUser().getId(), comment.getBook().getId()))
                                            .collect(Collectors.toList());
        return responses;
    }
}
