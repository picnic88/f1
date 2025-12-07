package com.dongyang.firstProject.controller;

import com.dongyang.firstProject.dto.AticleForm;
import com.dongyang.firstProject.entity.Article;
import com.dongyang.firstProject.entity.Comment;
import com.dongyang.firstProject.repository.ArticleRepository;
import com.dongyang.firstProject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // 데이터를 화면으로 넘길 때 필요
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/articles")
@Controller
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    //  글쓰기 페이지 보여주기
    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    // 글 저장하기 (Create)
    @PostMapping("/articles/create")
    public String createArticle(AticleForm form) { // DTO로 데이터 받기
        // DTO를 Entity로 변환
        Article article = form.toEntity();

        //Repository에게 Entity를 DB에 저장하게 함
        Article saved = articleRepository.save(article);

        System.out.println("DB 저장 완료: " + saved.getId()); // 로그 확인용

        // 저장 후 상세 페이지로 리다이렉트
        return "redirect:/articles/" + saved.getId();
    }

    @Autowired
    private CommentService commentService;

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        // 게시글 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 댓글 목록 가져오기
        List<Comment> comments = commentService.findAll(id);

        // 모델에 등록
        model.addAttribute("article", articleEntity);
        model.addAttribute("comments", comments); // 댓글 리스트 전달

        return "articles/show";
    }
}