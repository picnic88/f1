package com.dongyang.firstProject.repository;

import com.dongyang.firstProject.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {

}
