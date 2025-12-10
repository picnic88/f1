package com.dongyang.firstProject.service;

import com.dongyang.firstProject.entity.User;
import com.dongyang.firstProject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    // 로그인 확인
    @Transactional(readOnly = true)
    public Map<String, String> login(String loginId, String password) {
        Map<String, String> result = new HashMap<>();
        User user = userRepository.findByLoginId(loginId).orElse(null);

        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            result.put("status", "fail");
            return result;
        }

        result.put("status", "ok");
        result.put("role", String.valueOf(user.getRole())); // "ADMIN" or "USER"
        result.put("nickname", user.getNickname());
        return result;
    }

    // 회원 정보 수정
    public Map<String, String> updateMember(String loginId, Map<String, String> body) {
        Map<String, String> result = new HashMap<>();

        User user = userRepository.findByLoginId(loginId).orElse(null);
        if (user == null) {
            result.put("status", "fail");
            result.put("message", "회원을 찾을 수 없습니다.");
            return result;
        }

        // 비밀번호 변경 요청이 있으면
        String newPassword = body.get("password");
        if (newPassword != null && !newPassword.trim().isEmpty()) {
            user.setPassword(passwordEncoder.encode(newPassword));
        }

        // 닉네임 변경 요청이 있으면
        String newNickname = body.get("nickname");
        if (newNickname != null && !newNickname.trim().isEmpty()) {
            user.setNickname(newNickname);
        }

        userRepository.save(user); // 변경 내용 저장

        result.put("status", "ok");
        return result;
    }
}