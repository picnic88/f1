package com.dongyang.firstProject.dto;

import com.dongyang.firstProject.entity.Article;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
public class ArticleForm {

    private String title;
    private String content;
    private String boardType; //게시판 종류 필드 추가

    // DTO -> Entity 변환 메서드
    public Article toEntity() {
        return Article.builder()
                .title(title)
                .content(content)
                .boardType(boardType) // DB에 저장할 때도 이 종류를 같이 넣어줌
                .build();
    }
}