package com.dongyang.firstProject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class JoinDTO {
    private String loginId;
    private String password;
    private String nickname;
    private String role;
}