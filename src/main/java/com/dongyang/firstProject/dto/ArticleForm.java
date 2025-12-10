package com.dongyang.firstProject.dto;

import com.dongyang.firstProject.entity.Article;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
public class ArticleForm {
    private Long id; // 수정 시 식별을 위해 id 추가
    private String title;
    private String content;
    private String boardType;
    private String author;

    public Article toEntity() {
        return Article.builder()
                .id(id) // 엔티티 변환 시 id도 포함
                .title(title)
                .content(content)
                .boardType(boardType)
                .author(author)
                .build();
    }
}