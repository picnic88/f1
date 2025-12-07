package com.dongyang.firstProject.api;

import com.dongyang.firstProject.dto.AticleForm;
import com.dongyang.firstProject.entity.Article;
import com.dongyang.firstProject.repository.ArticleRepository;
import com.dongyang.firstProject.service.CommentService; // 댓글 서비스 재사용
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //데이터(JSON)를 반환
@RequestMapping("/api/articles") //localhost:8081/api/articles
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // 리액트(3000번 포트) 접속 허용
public class ArticleApiController {

    private final ArticleRepository articleRepository;
    private final CommentService commentService;

    //게시글 목록 조회 (GET)
    @GetMapping
    public List<Article> index() {
        return articleRepository.findAll();
    }

    //게시글 단건 조회 (GET)
    @GetMapping("/{id}")
    public Article show(@PathVariable Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    //게시글 생성 (POST)
    @PostMapping
    public Article create(@RequestBody AticleForm dto) {
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }

    //게시글 삭제 (DELETE)
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        Article target = articleRepository.findById(id).orElse(null);
        if (target != null) {
            articleRepository.delete(target);
            return "삭제 성공";
        }
        return "삭제 실패: 대상이 없습니다.";
    }
}