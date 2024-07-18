package com.example.jobKoreaIt.controller;

import com.example.jobKoreaIt.domain.common.service.RecruitServiceImpl;
import com.example.jobKoreaIt.domain.offer.entity.Recruit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/recruit")
public class RecruitController {

    @Autowired
    RecruitServiceImpl recruitServiceImpl;


    @GetMapping("/list")
    public void list_get(Model model){
        log.info("GET /recruit/list...");
        List<Recruit> list =  recruitServiceImpl.getAllRecruitDesc();
        System.out.println(list);
        model.addAttribute("list",list);
    }
    @GetMapping("/req")
    public void req(@RequestParam("id")Long id, Model model){
        log.info("GET /recruit/id..." + id);
        Recruit recruit=  recruitServiceImpl.getRecruit(id);
        System.out.println(recruit);
        model.addAttribute("recruit",recruit);
    }

}
