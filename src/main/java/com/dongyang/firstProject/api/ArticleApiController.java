package com.dongyang.firstProject.api;

import com.dongyang.firstProject.dto.ArticleForm;
import com.dongyang.firstProject.entity.Article;
import com.dongyang.firstProject.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ArticleApiController {

    private final ArticleRepository articleRepository;

    // 목록 조회 기능
    @GetMapping
    public List<Article> index(@RequestParam(value = "boardType", required = false) String boardType) {
        // 프론트가 "?boardType=NOTICE" 라고 물어봤다면
        if (boardType != null) {
            return articleRepository.findByBoardType(boardType); // 공지사항만 리턴
        }
        // 아무것도 안 물어봤으면
        return articleRepository.findAll(); // 전체 다 리턴
    }

    // 글 저장 (저장은 기존과 똑같지만, DTO에 boardType이 들어있어서 같이 저장됨)
    @PostMapping
    public Article create(@RequestBody ArticleForm dto) {
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }

    // 단건 조회
    @GetMapping("/{id}")
    public Article show(@PathVariable Long id) {
        return articleRepository.findById(id).orElse(null);
    }
}