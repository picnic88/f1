package com.dongyang.firstProject.api;

import com.dongyang.firstProject.dto.CommentDTO;
import com.dongyang.firstProject.entity.Comment;
import com.dongyang.firstProject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CommentApiController {

    private final CommentService commentService;

    // 특정 게시글의 댓글 목록 조회 (GET /api/comments?articleId=1)
    @GetMapping
    public List<Comment> comments(@RequestParam Long articleId) {
        return commentService.findAll(articleId);
    }

    //댓글 생성 (POST /api/comments?articleId=1&nickname=testUser)
    @PostMapping
    public String create(@RequestParam Long articleId,
                         @RequestParam String nickname,
                         @RequestBody CommentDTO dto) {

        commentService.create(articleId, dto, nickname);
        return "댓글 저장 성공";
    }
}