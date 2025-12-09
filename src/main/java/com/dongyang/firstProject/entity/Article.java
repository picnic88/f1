package com.dongyang.firstProject.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter // 수정(Setter) 기능 추가
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

    private String boardType; // NOTICE or BOARD

    private String author; // 작성자(아이디) 저장하는 칸

    private LocalDateTime createdDate;

    @PrePersist
    public void createDate() {
        this.createdDate = LocalDateTime.now();
    }

    // 글 수정 메서드
    public void patch(String title, String content) {
        if(title != null) this.title = title;
        if(content != null) this.content = content;
    }
}