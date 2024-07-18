package com.example.jobKoreaIt.controller.user.offer;

import com.example.jobKoreaIt.config.auth.PrincipalDetails;
import com.example.jobKoreaIt.domain.offer.dto.JobOfferDto;
import com.example.jobKoreaIt.domain.offer.dto.RecruitDto;
import com.example.jobKoreaIt.domain.offer.entity.Company;
import com.example.jobKoreaIt.domain.offer.entity.JobOffer;
import com.example.jobKoreaIt.domain.offer.entity.Recruit;
import com.example.jobKoreaIt.domain.offer.service.JobOfferServiceImpl;
import com.example.jobKoreaIt.domain.offer.service.jobopeningServicelmpl;
import com.example.jobKoreaIt.domain.seeker.entity.Apply;
import com.example.jobKoreaIt.domain.seeker.service.ApplyServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/offer")
public class jobOpeningController {

    @Autowired
    private JobOfferServiceImpl jobOfferService;

    @Autowired
    private jobopeningServicelmpl jobopeningServicelmpl;

    @Autowired
    private ApplyServiceImpl applyService;

    @GetMapping("/jobopening/add")
    public void jobadd() {
        log.info("채용공고 등록...");
    }

    @PostMapping("/jobopening/add")
    public  String jobaddPost(RecruitDto recruitDto, @AuthenticationPrincipal PrincipalDetails principalDetails, RedirectAttributes rttr) {
        log.info("채용공고 등록중..." + recruitDto);
        JobOfferDto jobOfferDto =  principalDetails.getJobOfferDto();
        jobopeningServicelmpl.jobopenadd(recruitDto,jobOfferDto);

        rttr.addFlashAttribute("message","채용공고 등록완료");
        return "redirect:/offer/jobopening/list";
    }
    @GetMapping("/jobopening/list")
    public void myRecruit(@AuthenticationPrincipal PrincipalDetails principalDetails,Model model){
        log.info("GET /seeker/jobopending/list.." + principalDetails);

        List<Recruit> list = jobopeningServicelmpl.getMyRecruit(principalDetails.getJobOfferDto());

        System.out.println(list);
        model.addAttribute("list",list);

    }


    @GetMapping("/jobopening/update")
    public void jobread(@RequestParam("id") Long id,Model model) {
        log.info("채용공고 조회...");
       Recruit recruit =   jobopeningServicelmpl.getMyRecruitOne(id);
       model.addAttribute("recruit",recruit);


    }
    @PostMapping("/jobopening/update")
    public String jobupdatePost(RecruitDto dto,RedirectAttributes rttr) {
        jobopeningServicelmpl.jobopenupdate(dto);
        rttr.addFlashAttribute("message","채용공고 수정을 완료하였습니다");
        return "redirect:/offer/jobopening/list";

    }

    @DeleteMapping("/jobopening/delete")
    public @ResponseBody ResponseEntity<String> jobdelete(@RequestParam("id") Long id) {
        log.info("채용공고 삭제중...");
        jobopeningServicelmpl.jobopenRemove(id);
        return new ResponseEntity("채용공고 삭제를 완료하였습니다.", HttpStatus.OK);
    }

    @GetMapping("/jobopening/applylist")
    public void applyList(@AuthenticationPrincipal PrincipalDetails principalDetails,Model model){
        List<Recruit> list = jobopeningServicelmpl.getMyRecruit(principalDetails.getJobOfferDto());
        System.out.println(list);
        model.addAttribute("list",list);
    }



}
