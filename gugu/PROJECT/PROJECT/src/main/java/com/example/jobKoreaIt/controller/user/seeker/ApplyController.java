package com.example.jobKoreaIt.controller.user.seeker;

import com.example.jobKoreaIt.domain.seeker.dto.ApplyDto;
import com.example.jobKoreaIt.domain.seeker.entity.Resume;
import com.example.jobKoreaIt.domain.seeker.repository.ResumeRepository;
import com.example.jobKoreaIt.domain.seeker.service.ApplyServiceImpl;
import com.example.jobKoreaIt.domain.seeker.service.ResumeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@Slf4j

public class ApplyController {

    @Autowired
    ResumeRepository resumeRepository;

    private final ApplyServiceImpl applyService;  // ApplyService 필드 추가

    @Autowired  // 의존성 주입을 위한 어노테이션
    public ApplyController(ApplyServiceImpl applyService) {
        this.applyService = applyService;
    }


    @GetMapping("/apply/add")
    public String Apply_get(Model model){
        log.info("GET/Apply/add/...");

        model.addAttribute("applyDto",new ApplyDto());
        return "/seeker/apply/add";
    }

    @PostMapping("/apply/add")
    public String Apply_Post(Model model,ApplyDto applyDto){
        log.info("Post/Apply/add...");
        log.info("applyDto : "+applyDto);
        Optional<Resume> resume=resumeRepository.findById(applyDto.getResume().getId());
        log.info("resume : "+resume);

        applyService.apply_add(applyDto,resume);

        model.addAttribute("applyDto",applyDto);
        model.addAttribute("resume",resume);
        return "redirect/seeker/apply/list";
    }
}
