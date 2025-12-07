package com.dongyang.firstProject.service;

import com.dongyang.firstProject.dto.CommentDTO;
import com.dongyang.firstProject.entity.Article;
import com.dongyang.firstProject.entity.Comment;
import com.dongyang.firstProject.entity.User;
import com.dongyang.firstProject.repository.ArticleRepository;
import com.dongyang.firstProject.repository.CommentRepository;
import com.dongyang.firstProject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    // 댓글 생성
    @Transactional
    public void create(Long articleId, CommentDTO dto, String loginId) {
        // 게시글 조회
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 쓰기 실패: 게시글이 없습니다."));

        // 유저 조회 (로그인한 아이디로)
        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 쓰기 실패: 유저를 찾을 수 없습니다."));

        // 댓글 엔티티 생성
        Comment comment = Comment.builder()
                .body(dto.getBody())
                .article(article)
                .user(user)
                .build();

        // 저장
        commentRepository.save(comment);
    }

    // 게시글의 댓글 목록 조회
    public List<Comment> findAll(Long articleId) {
        return commentRepository.findByArticleId(articleId);
    }
}