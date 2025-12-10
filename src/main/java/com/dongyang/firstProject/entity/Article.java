package com.dongyang.firstProject.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
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

    private String author; // 작성자(아이디)

    private LocalDateTime createdDate;

    @PrePersist
    public void createDate() {
        this.createdDate = LocalDateTime.now();
    }

    //리액트에서 수정 요청이 오면 이 메서드가 실행됨
    public void patch(String title, String content) {
        if(title != null && !title.isEmpty()) this.title = title;
        if(content != null && !content.isEmpty()) this.content = content;
    }
}