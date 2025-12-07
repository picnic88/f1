package com.dongyang.firstProject.dto;

import com.dongyang.firstProject.entity.Article;

public class AticleForm {

    private String title;
    private String content;

    // 생성자
    public AticleForm(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // Getter & Setter
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    // DTO에서 Entity 변환 메서드
    public Article toEntity() {
        return new Article(null, title, content);
    }
}