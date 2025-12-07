package com.dongyang.firstProject.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title; // 제목

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content; // 내용

    @Column(nullable = false)
    private String writer; // 작성자 (닉네임이나 ID를 저장)

    // User 객체와 직접 연결
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime createdDate; // 등록일

    @PrePersist
    public void prePersist() {
        this.createdDate = LocalDateTime.now();
    }

    @Builder
    public Post(String title, String content, String writer, User user) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.user = user;
    }
}
