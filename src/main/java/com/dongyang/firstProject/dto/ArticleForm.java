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
    private String boardType;
    private String author; //작성자 추가

    public Article toEntity() {
        return Article.builder()
                .title(title)
                .content(content)
                .boardType(boardType)
                .author(author) //엔티티로 전달
                .build();
    }
}