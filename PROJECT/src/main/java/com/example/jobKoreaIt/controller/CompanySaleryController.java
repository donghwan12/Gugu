package com.example.jobKoreaIt.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class CompanySaleryController {


    @GetMapping("/company")
    public String getCompany() {
        log.info("GET /company/...!");
        return "companySalary/company";  // 정확한 뷰 이름
    }
}
