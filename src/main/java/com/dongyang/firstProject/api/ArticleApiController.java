package com.dongyang.firstProject.api;

import com.dongyang.firstProject.dto.ArticleForm;
import com.dongyang.firstProject.entity.Article;
import com.dongyang.firstProject.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.DELETE, RequestMethod.PUT})
public class ArticleApiController {

    private final ArticleRepository articleRepository;

    // 목록 조회
    @GetMapping
    public List<Article> index(@RequestParam(value = "boardType", required = false) String boardType,
                               @RequestParam(value = "author", required = false) String author) {
        if (author != null) {
            return articleRepository.findByAuthor(author); // 내 글만 조회
        }
        if (boardType != null) {
            return articleRepository.findByBoardType(boardType);
        }
        return articleRepository.findAll();
    }

    // 글 생성
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

    // 글 삭제 API
    @DeleteMapping("/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        Article target = articleRepository.findById(id).orElse(null);
        if (target == null) return ResponseEntity.badRequest().build();
        articleRepository.delete(target);
        return ResponseEntity.ok().build();
    }

    // 글 수정 API
    @PatchMapping("/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto) {
        Article target = articleRepository.findById(id).orElse(null);
        if (target == null) return ResponseEntity.badRequest().build();
        target.patch(dto.getTitle(), dto.getContent());
        Article updated = articleRepository.save(target);
        return ResponseEntity.ok(updated);
    }
}