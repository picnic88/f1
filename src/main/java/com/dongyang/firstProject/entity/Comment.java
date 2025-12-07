package com.dongyang.firstProject.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 댓글 고유 번호

    @Column(nullable = false, length = 500)
    private String body; // 댓글 내용

    //게시글과 연결 (다대일 관계)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id") // DB에는 article_id로 저장됨
    private Article article;

    //작성자와 연결 (다대일 관계)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // DB에는 user_id로 저장됨
    private User user;

    private LocalDateTime createdDate; // 댓글 작성 시간

    @PrePersist // DB에 저장되기 직전에 실행
    public void createDate() {
        this.createdDate = LocalDateTime.now();
    }
}