package com.example.jobKoreaIt.controller.user.seeker;

import com.example.jobKoreaIt.domain.offer.dto.RecruitDto;
import com.example.jobKoreaIt.domain.offer.entity.Recruit;
import com.example.jobKoreaIt.domain.offer.repository.RecruitRepository;
import com.example.jobKoreaIt.domain.seeker.dto.ApplyDto;
import com.example.jobKoreaIt.domain.seeker.entity.Apply;
import com.example.jobKoreaIt.domain.seeker.entity.Resume;
import com.example.jobKoreaIt.domain.seeker.repository.ResumeRepository;
import com.example.jobKoreaIt.domain.seeker.service.ApplyServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j

public class ApplyController {

    @Autowired
    ResumeRepository resumeRepository;

    @Autowired
    RecruitRepository recruitRepository;

    private final ApplyServiceImpl applyService;  // ApplyService 필드 추가

    @Autowired  // 의존성 주입을 위한 어노테이션
    public ApplyController(ApplyServiceImpl applyService) {
        this.applyService = applyService;
    }


    //입사지원서 작성
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
        Recruit Recruit = recruitRepository.findByTitle(applyDto.getRecruit().getTitle());
        log.info("resume : "+resume);
        log.info("Recruit : "+Recruit);

        applyService.apply_add(applyDto,resume,Recruit);

        model.addAttribute("applyDto",applyDto);
        model.addAttribute("resume",resume);
        return "redirect:/apply/list";
    }

    //내 입사지원서 목록보기
    @GetMapping("/apply/list")
    public String Apply_get_list(Model model){
        log.info("GET/apply/list...");
        List<ApplyDto> applyDtoList =applyService.apply_list();
        model.addAttribute("applyDtoList",applyDtoList);
        return "seeker/apply/list";
    }

    //입사지원사 상세읽기
    @GetMapping("/seeker/apply/read/{id}")
    public String Apply_Get_read(@PathVariable("id") Long id,Model model){
        log.info("GET/apply/read/..." +id);

        Optional<Apply> applyOptional = applyService.applyRead(id);
        log.info("applyOptional : "+applyOptional);
        if(applyOptional.isPresent()){
            Apply apply=applyOptional.get();
            model.addAttribute("apply",apply);
        }else{
            model.addAttribute("notFound","입사지원서를 찾을수가없습니다.");

        }
        return "seeker/apply/read";
    }

    //입사지원서 삭제 ======
    @GetMapping("/apply/delete/{id}")
    public String Apply_delete_Get(){
        log.info("GET/Apply_delte...");
        return "redirect:/apply/list";
    }

    @PostMapping("/seeker/apply/delete/{id}")
    public String apply_post_delte(@PathVariable("id")Long id){
        log.info("Post/apply/delete... "+id);
        applyService.applyDelete(id);
        return "redirect:/apply/list";
    }
}
