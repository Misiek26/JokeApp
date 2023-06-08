package com.JokeApp.Project.controller;

import com.JokeApp.Project.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

@RestController
@AllArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/api/signin")
    public void signIn(@RequestParam String email, @RequestParam String password){
        String token = loginService.signInUser(email, password);
//        Cookie tokenCookie = new Cookie("token", token);
//        tokenCookie.setMaxAge(3600);
//        tokenCookie.setPath("/");

    }
}
