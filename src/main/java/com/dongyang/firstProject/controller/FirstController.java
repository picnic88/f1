package com.dongyang.firstProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class FirstController {
    @GetMapping("/hi") //클라이언트의 요청이 get방식으로 들어오는 것
    public String hello(){

        return  "helloView";
    }
    @GetMapping("/")
    public String home() {
        return "home";
    }
}

