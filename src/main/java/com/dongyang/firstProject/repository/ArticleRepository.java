package com.dongyang.firstProject.repository;

import com.dongyang.firstProject.entity.Article;
import org.springframework.data.repository.CrudRepository;
import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    @Override
    ArrayList<Article> findAll();
    ArrayList<Article> findByBoardType(String boardType);

    //작성자로 글 찾기 (내 글 보기용)
    ArrayList<Article> findByAuthor(String author);
}