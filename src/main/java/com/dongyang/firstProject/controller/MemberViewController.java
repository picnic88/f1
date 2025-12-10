package com.dongyang.firstProject.controller;

import com.dongyang.firstProject.config.PrincipalDetails;
import com.dongyang.firstProject.entity.User;
import com.dongyang.firstProject.repository.UserRepository;
import com.dongyang.firstProject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MemberViewController {

    private final UserRepository userRepository;
    private final MemberService memberService;

    // 내 정보 수정 페이지 보여주기
    @GetMapping("/members/my-page")
    public String myPage(@AuthenticationPrincipal PrincipalDetails principal, Model model) {
        if (principal == null) {
            return "redirect:/login"; // 로그인 안 했으면 로그인 창으로
        }

        // 현재 로그인된 유저 정보 가져오기
        User user = userRepository.findByLoginId(principal.getUsername()).orElse(null);
        model.addAttribute("user", user);

        return "members/myPage";
    }

    // 내 정보 수정 처리
    @PostMapping("/members/update")
    public String updateMember(@AuthenticationPrincipal PrincipalDetails principal,
                               @RequestParam(required = false) String password,
                               @RequestParam(required = false) String nickname) {

        if (principal == null) return "redirect:/login";

        // 서비스에 넘겨줄 Map 생성
        Map<String, String> body = new HashMap<>();
        if (password != null && !password.isEmpty()) body.put("password", password);
        if (nickname != null && !nickname.isEmpty()) body.put("nickname", nickname);

        // MemberService의 updateMember 메서드 호출
        memberService.updateMember(principal.getUsername(), body);

        return "redirect:/members/my-page";
    }
}