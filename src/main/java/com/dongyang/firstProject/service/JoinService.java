package com.dongyang.firstProject.service;

import com.dongyang.firstProject.dto.JoinDTO;
import com.dongyang.firstProject.entity.User;
import com.dongyang.firstProject.entity.UserRole;
import com.dongyang.firstProject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void joinProcess(JoinDTO joinDTO) {

        if (userRepository.existsByLoginId(joinDTO.getLoginId())) {
            return; // 이미 아이디가 있으면 가입 중단
        }

        String rawPassword = joinDTO.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);

        UserRole role = UserRole.USER;
        if ("ADMIN".equals(joinDTO.getRole())) {
            role = UserRole.ADMIN;
        }

        User user = User.builder()
                .loginId(joinDTO.getLoginId())
                .password(encPassword)
                .nickname(joinDTO.getNickname())
                .role(role)
                .build();

        userRepository.save(user);
    }
}