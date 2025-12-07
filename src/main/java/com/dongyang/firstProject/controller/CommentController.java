package com.dongyang.firstProject.controller;

import com.dongyang.firstProject.config.PrincipalDetails;
import com.dongyang.firstProject.dto.CommentDTO;
import com.dongyang.firstProject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 저장 요청 (POST)
    @PostMapping("/articles/{id}/comments")
    public String createComment(@PathVariable Long id, CommentDTO dto,
                                @AuthenticationPrincipal PrincipalDetails principalDetails) {

        if (principalDetails == null) {
            return "redirect:/login"; // 로그인 안 했으면 로그인 페이지로 감
        }

        // 서비스에게 저장을 시킴 (게시글ID, 댓글내용, 로그인한 유저ID)
        commentService.create(id, dto, principalDetails.getUsername());

        return "redirect:/articles/" + id; // 댓글 쓴 뒤 다시 게시글 상세페이지로
    }
}