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

    //목록 조회 (필터링 포함)
    @GetMapping
    public List<Article> index(@RequestParam(value = "boardType", required = false) String boardType,
                               @RequestParam(value = "author", required = false) String author) {
        if (author != null) {
            return articleRepository.findByAuthor(author); // 내 글만 보기
        }
        if (boardType != null) {
            return articleRepository.findByBoardType(boardType); // 게시판 종류별 보기
        }
        return articleRepository.findAll();
    }

    // 글 생성
    @PostMapping
    public Article create(@RequestBody ArticleForm dto) {
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }

    //단건 조회 (상세보기)
    @GetMapping("/{id}")
    public Article show(@PathVariable Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    // 글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        Article target = articleRepository.findById(id).orElse(null);
        if (target == null) return ResponseEntity.badRequest().build();
        articleRepository.delete(target);
        return ResponseEntity.ok().build();
    }

    // 글 수정 (ArticleEdit.tsx와 연결됨)
    @PatchMapping("/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto) {
        Article target = articleRepository.findById(id).orElse(null);

        if (target == null) return ResponseEntity.badRequest().build();

        // 기존 데이터에 새 내용을 덮어씌움 (Patch)
        target.patch(dto.getTitle(), dto.getContent());

        Article updated = articleRepository.save(target);
        return ResponseEntity.ok(updated);
    }
}