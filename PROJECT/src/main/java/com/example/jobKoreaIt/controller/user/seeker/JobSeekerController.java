package com.example.jobKoreaIt.controller.user.seeker;

import com.example.jobKoreaIt.config.auth.PrincipalDetails;
import com.example.jobKoreaIt.domain.common.dto.UserDto;
import com.example.jobKoreaIt.domain.seeker.dto.JobSeekerDto;
import com.example.jobKoreaIt.domain.seeker.entity.Apply;
import com.example.jobKoreaIt.domain.seeker.service.ApplyServiceImpl;
import com.example.jobKoreaIt.domain.seeker.service.JobSeekerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/seeker")
public class JobSeekerController {

    @Autowired
    private  JobSeekerServiceImpl jobSeekerServiceImpl;


    @GetMapping("/join")
    public String join_get(){
        log.info("GET /seeker/join...");
        return "join"; // return the view name
    }

    @PostMapping("/join")
    public String seekerJoin(@ModelAttribute UserDto userDto, @ModelAttribute JobSeekerDto jobSeekerDto, BindingResult bindingResult, Model model)
    {
        log.info("POST /seeker/join..userDto : " + userDto + " SeekerDto : " + jobSeekerDto);
//        //유효성 검사
//        if (bindingResult.hasFieldErrors()) {
//            for (FieldError error : bindingResult.getFieldErrors()) {
//                log.info("ErrorField : " + error.getField() + " ErrorMsg : " + error.getDefaultMessage());
//                model.addAttribute(error.getField(), error.getDefaultMessage());
//            }
//            return "/user/join";
//        }

        jobSeekerServiceImpl.addJobSeeker(userDto,jobSeekerDto);

        return "login";
    }

    @PostMapping("/confirm_id")
    public @ResponseBody ResponseEntity<Map<String,Object>> confirm_post_id(
            @RequestParam("username")   String username,
            @RequestParam("tel")   String tel
    )
    {
        Map<String,Object> result  = jobSeekerServiceImpl.findUserId(username,tel);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/confirm_pw")
    public @ResponseBody ResponseEntity<Map<String,Object>> confirm_post_pw(
            @RequestParam("userid")   String userid,
            @RequestParam("username")   String username,
            @RequestParam("tel")   String tel
    )
    {
        Map<String,Object> result  = jobSeekerServiceImpl.findUserPw(userid,username,tel);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/myinfo/read")
    public void myinfo(){

    }

    @Autowired
    private ApplyServiceImpl applyService;

    @GetMapping("/myinfo/applylist")
    public void applylist(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model){
        List<Apply> list =  applyService.getSeekerApply(principalDetails);
        model.addAttribute("list",list);

    }
}
