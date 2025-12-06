package com.dongyang.firstProject.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor // 파라미터 없는 기본 생성자 자동 생성
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PDF 스타일대로 변수명을 간결하게 id로 변경

    @Column(nullable = false, unique = true)
    private String loginId; // 아이디

    @Column(nullable = false)
    private String password; // 비밀번호

    @Column(nullable = false)
    private String nickname; // 닉네임

    @Enumerated(EnumType.STRING) // DB에 "USER", "ADMIN" 문자열로 저장
    @Column(nullable = false)
    private Role role; // 37페이지 Role Enum 사용

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    // 빌더 패턴 (객체 생성을 깔끔하게 하기 위함)
    @Builder
    public User(String loginId, String password, String nickname, Role role) {
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
    }
}
