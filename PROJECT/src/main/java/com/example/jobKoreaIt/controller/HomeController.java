package com.example.jobKoreaIt.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class HomeController {


    @GetMapping("/")
    public String index(Authentication authentication){
        log.info("GET / ...authentication : " + authentication);
        return "index";
    }


}
