package com.hoshimusubi.suhwa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Main {

    @GetMapping("/")
    public String home() {
    	System.out.println(">>> home controller called");
        return "home"; // → /WEB-INF/views/Home.jsp 를 의미함
    }
}