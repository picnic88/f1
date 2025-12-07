package com.dongyang.firstProject.repository;

import com.dongyang.firstProject.entity.Article;
import org.springframework.data.repository.CrudRepository;
import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article, Long> {

    // Iterable 대신 ArrayList(List의 일종)를 반환하도록 재정의
    @Override
    ArrayList<Article> findAll();
}