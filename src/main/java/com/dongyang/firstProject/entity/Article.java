package com.dongyang.firstProject.entity;

import jakarta.persistence.*; // 스프링부트 3.x라면 jakarta, 2.x라면 javax
// import javax.persistence.*; // 만약 윗줄에서 에러나면 주석 해제하고 윗줄을 주석 처리

@Entity // DB가 인식하는 객체로 선언
public class Article {

    @Id // 대표값 (주민등록번호 같은 것)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 알아서 1, 2, 3... 번호 생성
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    // 기본 생성자 (JPA 필수)
    public Article() {}

    // 데이터를 넣을 때 쓸 생성자
    public Article(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    // Getter (데이터 조회용)
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
}