package com.dongyang.firstProject.repository;

import com.dongyang.firstProject.entity.Article;
import org.springframework.data.repository.CrudRepository;
import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    @Override
    ArrayList<Article> findAll();

    // boardType이 일치하는 것만 찾는 기능 추가
    // JPA가 알아서 SQL을 만들어줌
    ArrayList<Article> findByBoardType(String boardType);
}