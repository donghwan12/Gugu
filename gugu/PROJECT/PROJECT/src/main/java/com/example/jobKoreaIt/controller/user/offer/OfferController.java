package com.example.jobKoreaIt.controller.user.offer;

import com.example.jobKoreaIt.domain.common.dto.UserDto;
import com.example.jobKoreaIt.domain.offer.dto.JobOfferDto;
import com.example.jobKoreaIt.domain.offer.service.JobOfferServiceImpl;
import com.example.jobKoreaIt.domain.seeker.dto.JobSeekerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Controller
@Slf4j
@RequestMapping("/offer")
public class OfferController {

    @Autowired
    private JobOfferServiceImpl jobOfferService;
    @GetMapping("/join")
    public String join_get(){
        log.info("GET /seeker/join...");
        return "join"; // return the view name
    }

    @PostMapping("/join")
    public String seekerJoin(@ModelAttribute UserDto userDto, @ModelAttribute JobOfferDto jobOfferDto, BindingResult bindingResult, Model model)
    {
        log.info("POST /seeker/join..userDto : " + userDto + " SeekerDto : " + jobOfferDto);
//        //유효성 검사
//        if (bindingResult.hasFieldErrors()) {
//            for (FieldError error : bindingResult.getFieldErrors()) {
//                log.info("ErrorField : " + error.getField() + " ErrorMsg : " + error.getDefaultMessage());
//                model.addAttribute(error.getField(), error.getDefaultMessage());
//            }
//            return "/user/join";
//        }

        jobOfferService.addJobOfferDto(userDto,jobOfferDto);

        return "login";
    }

    @PostMapping("/confirm_id")
    public @ResponseBody ResponseEntity<Map<String,Object>> confirm_post_id(
            @RequestParam("companyNumber")   String companyNumber,
            @RequestParam("companyEmail")   String companyEmail
    )
    {

        log.info("POST /offer/confirm_id...companyNumber : " +companyNumber + " companyEmail : " + companyEmail );

        Map<String,Object> result  = jobOfferService.findUserId(companyNumber,companyEmail);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/confirm_pw")
    public @ResponseBody ResponseEntity<Map<String,Object>> confirm_post_pw(
            @RequestParam("userid")   String userid,
            @RequestParam("companyNumber")   String companyNumber,
            @RequestParam("companyEmail")   String companyEmail
    )
    {
        Map<String,Object> result  = jobOfferService.findUserPw(userid,companyNumber,companyEmail);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}


