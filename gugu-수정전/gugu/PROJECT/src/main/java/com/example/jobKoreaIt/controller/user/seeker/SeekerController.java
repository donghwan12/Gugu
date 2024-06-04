package com.example.jobKoreaIt.controller.user.seeker;

import com.example.jobKoreaIt.domain.seeker.dto.ResumeDto;

import com.example.jobKoreaIt.domain.seeker.entity.Resume;
import com.example.jobKoreaIt.domain.seeker.service.JobSeekerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/seeker")
public class SeekerController {

    @Autowired
    private JobSeekerServiceImpl jobSeekerServiceImpl;

    public SeekerController(JobSeekerServiceImpl jobSeekerServiceImpl) {
        this.jobSeekerServiceImpl = jobSeekerServiceImpl;
    }

    @GetMapping("/join")
    public String join_get(){
        log.info("GET /seeker/join...");
        return "join"; // return the view name
    }

    @GetMapping("/resume/add")
    public String resume_add_get(Model model){
        log.info("GET /resume/add..");
        model.addAttribute("resume", new Resume());
        return "seeker/resume/add"; // return the view name
    }

    @PostMapping("/resume/add")
    public String resume_add_post(Resume resume){
        log.info("POST /resume/add..");
        jobSeekerServiceImpl.resume_add(resume);
        return "redirect:/seeker/resume/list"; // redirect to the resume list after adding
    }

    @GetMapping("/resume/update/{id}")
    public String resume_update_get(@PathVariable("id")Long id){
        log.info("GET /resume/update.." +id);
        return "seeker/resume/update"; // return the view name
    }

    @GetMapping("/resume/read/{id}")
    public String resume_read_get(@PathVariable("id") Long id, Model model) {
        log.info("GET /resume/read..");
        Optional<Resume> resumeOptional = jobSeekerServiceImpl.resume_read(id);
        if (resumeOptional.isPresent()) {
            Resume resume = resumeOptional.get();
            model.addAttribute("resume", resume);
        } else {
            // 해당 ID에 해당하는 이력서를 찾을 수 없는 경우에 대한 처리
            // 여기서는 간단히 "notFound"라는 문자열을 모델에 추가하여 나중에 뷰에서 처리할 수 있도록 합니다.
            model.addAttribute("notFound", "이력서를 찾을 수 없습니다.");
        }
        return "seeker/resume/read"; // return the view name
    }


    @GetMapping("/resume/list")
    public String resume_list_get(Resume resume,Model model){
        log.info("GET /resume/list..");
        List<ResumeDto> resumesList= jobSeekerServiceImpl.resume_list();
        model.addAttribute("resumeList",resumesList);
        return "seeker/resume/list"; // return the view name
    }

    @PostMapping("/resume/update")
    public String resume_update_post(){
        log.info("POST /resume/update..");
        return "redirect:/seeker/resume/list"; // redirect after updating
    }

    @PostMapping("/resume/read")
    public String resume_read_post(){
        log.info("POST /resume/read..");
        return "redirect:/seeker/resume/list"; // redirect after reading
    }

    @PostMapping("/resume/list")
    public String resume_list_post(){
        log.info("POST /resume/list..");
        return "redirect:/seeker/resume/list"; // redirect after listing
    }
}
