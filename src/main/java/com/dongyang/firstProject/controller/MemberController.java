package com.dongyang.firstProject.controller;

import com.dongyang.firstProject.dto.JoinDTO;
import com.dongyang.firstProject.entity.User;
import com.dongyang.firstProject.repository.UserRepository;
import com.dongyang.firstProject.service.JoinService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class MemberController {

    private final JoinService joinService;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    // 로그인 (JSON으로 role 정보까지 반환)
    @PostMapping("/loginProc")
    public Map<String, String> loginProcess(@RequestBody LoginDTO loginDTO) {
        Map<String, String> result = new HashMap<>();

        User user = userRepository.findByLoginId(loginDTO.getLoginId()).orElse(null);

        if (user == null || !passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            result.put("status", "fail");
            return result;
        }

        // 성공 시 status와 함께 role(권한)도 보냄
        result.put("status", "ok");
        result.put("role", String.valueOf(user.getRole())); // "USER" 아니면 "ADMIN"
        result.put("nickname", user.getNickname()); // 닉네임도 보내줌

        return result;
    }

    @PostMapping("/joinProc")
    public String joinProcess(@RequestBody JoinDTO joinDTO) {
        try {
            joinService.joinProcess(joinDTO);
            return "ok";
        } catch (Exception e) {
            return "fail";
        }
    }

    @Data
    static class LoginDTO {
        private String loginId;
        private String password;
    }
}