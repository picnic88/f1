package com.dongyang.firstProject.controller;

import com.dongyang.firstProject.dto.JoinDTO;
import com.dongyang.firstProject.service.JoinService;
import com.dongyang.firstProject.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
//리액트(5173)의 모든 요청(GET, POST, PATCH 등)을 허용
@CrossOrigin(origins = "http://localhost:5173", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.DELETE, RequestMethod.PUT})
public class MemberController {

    private final JoinService joinService;
    private final MemberService memberService;

    // 로그인
    @PostMapping("/loginProc")
    public Map<String, String> loginProcess(@RequestBody LoginDTO loginDTO) {
        return memberService.login(loginDTO.getLoginId(), loginDTO.getPassword());
    }

    //회원가입
    @PostMapping("/joinProc")
    public String joinProcess(@RequestBody JoinDTO joinDTO) {
        try {
            joinService.joinProcess(joinDTO);
            return "ok";
        } catch (Exception e) {
            return "fail";
        }
    }

    // 회원 정보 수정 (MyPage.tsx와 연결됨)
    @PatchMapping("/api/members/{loginId}")
    public Map<String, String> updateMember(@PathVariable String loginId, @RequestBody Map<String, String> body) {
        return memberService.updateMember(loginId, body);
    }

    @Data
    static class LoginDTO {
        private String loginId;
        private String password;
    }
}