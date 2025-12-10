package com.dongyang.firstProject.controller;

import com.dongyang.firstProject.dto.ArticleForm;
import com.dongyang.firstProject.entity.Article;
import com.dongyang.firstProject.entity.Comment;
import com.dongyang.firstProject.repository.ArticleRepository;
import com.dongyang.firstProject.service.CommentService;
import lombok.RequiredArgsConstructor; // Autowired 대신 생성자 주입 권장
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor // final이 붙은 필드를 자동으로 주입해 줌 (추천 방식)
public class ArticleController {

    private final ArticleRepository articleRepository;
    private final CommentService commentService;

    // 글쓰기 페이지
    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    // 글 저장
    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
        Article article = form.toEntity();
        Article saved = articleRepository.save(article);
        return "redirect:/articles/" + saved.getId();
    }

    // 글 상세 보기
    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        Article articleEntity = articleRepository.findById(id).orElse(null);
        List<Comment> comments = commentService.findAll(id);

        model.addAttribute("article", articleEntity);
        model.addAttribute("comments", comments);

        return "articles/show";
    }

    // 글 수정 페이지 이동
    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        // 수정할 데이터를 가져와서 모델에 등록
        Article articleEntity = articleRepository.findById(id).orElse(null);
        model.addAttribute("article", articleEntity);

        return "articles/edit"; // articles/edit.mustache(혹은 jsp) 파일 필요
    }

    // 글 수정 처리
    @PostMapping("/articles/update")
    public String update(ArticleForm form) {
        //  DTO를 엔티티로 변환
        Article articleEntity = form.toEntity();

        // DB에서 기존 데이터 찾기
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);

        // 기존 데이터가 있다면 덮어쓰기 (patch 메서드 활용)
        if (target != null) {
            // Article 엔티티에 있는 patch 메서드 사용
            target.patch(articleEntity.getTitle(), articleEntity.getContent());
            articleRepository.save(target); // 변경사항 저장
        }

        return "redirect:/articles/" + articleEntity.getId();
    }

    // 글 삭제
    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id) {
        Article target = articleRepository.findById(id).orElse(null);

        if (target != null) {
            articleRepository.delete(target);
        }

        return "redirect:/articles"; // 목록 페이지로 리다이렉트
    }
}