package com.dongyang.firstProject.controller;

import com.dongyang.firstProject.dto.AticleForm; // 수정된 DTO 이름 import
import com.dongyang.firstProject.entity.Article;
import com.dongyang.firstProject.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // 데이터를 화면으로 넘길 때 필요
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    // 1. 글쓰기 페이지 보여주기
    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new"; // templates/articles/new.mustache 파일로 이동
    }

    // 2. 글 저장하기 (Create)
    @PostMapping("/articles/create")
    public String createArticle(AticleForm form) { // DTO로 데이터 받기
        // 1. DTO를 Entity로 변환
        Article article = form.toEntity();

        // 2. Repository에게 Entity를 DB에 저장하게 함
        Article saved = articleRepository.save(article);

        System.out.println("DB 저장 완료: " + saved.getId()); // 로그 확인용

        // 3. 저장 후 상세 페이지로 리다이렉트 (새로고침 시 중복 등록 방지)
        return "redirect:/articles/" + saved.getId();
    }

    // 3. 글 상세 조회하기 (Read)
    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        // 1. id로 데이터를 가져옴! (없으면 null 반환)
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 2. 가져온 데이터를 모델에 등록 (이름: "article")
        model.addAttribute("article", articleEntity);

        // 3. 보여줄 페이지 설정
        return "articles/show"; // templates/articles/show.mustache 파일로 이동
    }
}