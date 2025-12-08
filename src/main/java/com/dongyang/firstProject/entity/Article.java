package com.dongyang.firstProject.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column
    private String boardType; // DB 테이블에 'board_type' 칸 생성

    private LocalDateTime createdDate;

    @PrePersist
    public void createDate() {
        this.createdDate = LocalDateTime.now();
    }
}