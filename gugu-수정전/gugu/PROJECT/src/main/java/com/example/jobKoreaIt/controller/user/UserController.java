package com.example.jobKoreaIt.controller.user;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class UserController {

    @GetMapping("/user/login")
    public void login_get(){
        log.info("GET /login...");
    }

    @GetMapping("/user/join")
    public void join_get(){
        log.info("GET /join...");
    }
    @PostMapping("/user/join")
    public void join_post(){
        log.info("POST /join...");
    }

    @PostMapping("/user/myinfo")
    public void myinfo(){
        log.info("GET /myinfo...");
    }

}
