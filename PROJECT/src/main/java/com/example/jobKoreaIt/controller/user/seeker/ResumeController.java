package com.example.jobKoreaIt.controller.user.seeker;

import com.example.jobKoreaIt.config.auth.PrincipalDetails;
import com.example.jobKoreaIt.domain.common.dto.UserDto;
import com.example.jobKoreaIt.domain.seeker.dto.*;
import com.example.jobKoreaIt.domain.seeker.entity.Carrer;
import com.example.jobKoreaIt.domain.seeker.entity.Resume;
import com.example.jobKoreaIt.domain.seeker.repository.CareerRepository;
import com.example.jobKoreaIt.domain.seeker.service.ResumeServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/seeker")
public class ResumeController {
    //------------------
    //이력서 CRUD (이동환)
    //------------------

    @Autowired
    private ResumeServiceImpl resumeServiceImpl;


    //이력서 작성---
    @GetMapping("/resume/add")
    public String resume_add_get(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model){
        log.info("GET /resume/add..");
        JobSeekerDto jobSeekerDto = principalDetails.getJobSeekerDto();

        model.addAttribute("jobSeekerDto", jobSeekerDto);
        return "seeker/resume/add"; // return the view name
    }

    @PostMapping("/resume/add")
    public @ResponseBody  void resume_add_post(
            @RequestPart("file") MultipartFile file,
            @RequestParam Map<String, String> formData,
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ) throws IOException {
        System.out.println(formData);
        String title = formData.get("title");
        String name = formData.get("name");
        String email = formData.get("email");
        String phone = formData.get("phone");
        String schoolName = formData.get("schoolName");
        String major = formData.get("major");
        String graduationYear = formData.get("graduationYear");
        String summary = formData.get("summary");

        System.out.println("file : " + file);


        String carrer =formData.get("carrer");
        ObjectMapper objectMapper = new ObjectMapper();
        CarrerDto [] carrerDtos = objectMapper.readValue(carrer,CarrerDto[].class);

        String certification = formData.get("certification");
        CertificationDto [] certificationDtos = objectMapper.readValue(certification,CertificationDto[].class);

        System.out.println("name : " + name);
        System.out.println("email : " + email);
        System.out.println("phone : " + phone);
        System.out.println("schoolName : " + schoolName);
        System.out.println("major : " + major);
        System.out.println("graduationYear : " + graduationYear);
//        System.out.println("carrerDtos : " + carrerDtos);
        for(CarrerDto carrerDto : carrerDtos)
            System.out.println(carrerDto);
        System.out.println("certification : " + certification);
        for(CertificationDto certificationDto : certificationDtos)
            System.out.println(certificationDto);

        ResumeDto resumeDto = new ResumeDto();
        resumeDto.setTitle(title);
        resumeDto.setName(name);
        resumeDto.setPhone(phone);
        resumeDto.setEmail(email);
        resumeDto.setSchoolName(schoolName);
        resumeDto.setMajor(major);
        resumeDto.setGraduationYear(graduationYear); //LocalDateTime.parse(graduationYear, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        resumeDto.setCarrer(carrerDtos);
        resumeDto.setCertification(certificationDtos);
        resumeDto.setSummary(summary);
        resumeDto.setFile(file);

        UserDto userDto = principalDetails.getUserDto();
        resumeDto.setUserid(userDto.getUserid());

        resumeServiceImpl.addResume(resumeDto);
    }

    //수정 ------------------------------------------------------------

    @Autowired
    CareerRepository careerRepository;
    @GetMapping("/resume/update/{id}")
    public String resume_update_get(@PathVariable("id") long id, Model model) {
        log.info("id : "+id);
        log.info("GET /resume/update..");

        return null;
    }

    @PostMapping("/resume/update")
    public String resume_update_post(ResumeFormDto formDto) {
        log.info("formDto : "+formDto);
        Long id=formDto.getResume().getId();
        log.info("formDto.id : "+id);
        // Update the resume
        resumeServiceImpl.resume_update(id, formDto);



        return "redirect:/seeker/resume/list";

    }



    //상세읽기--------------------------------------------------------------
    @GetMapping("/resume/read/{id}")
    public String resume_read_get(@PathVariable("id") Long id, Model model) {
        log.info("GET /resume/read..");

        Map<String,Object> result = resumeServiceImpl.resume_read(id);

        model.addAttribute("resume",  result.get("resume"));
        model.addAttribute("carrerList", result.get("carrerList"));
        model.addAttribute("certificationList", result.get("certificationList"));
        System.out.println( result.get("resume"));
        return "seeker/resume/read"; // return the view name
    }



    //이력서 항목 리스트 조회------------------------
    @GetMapping("/resume/list")
    public String resume_list_get(Model model){
        log.info("GET /resume/list..");
        List<ResumeDto> resumesList= resumeServiceImpl.resume_list();
        model.addAttribute("resumeList",resumesList);
        return "seeker/resume/list"; // return the view name
    }



    //이력서 삭제--------------------------------------------
    @GetMapping("/resume/delete/{id}")
    public String resume_get_delete(){
        log.info("Get/resume/delete/..");
        return "redirect:/seeker/resume/list";
    }

    @PostMapping("/resume/delete/{id}")
    public String resume_post_delete(@PathVariable("id")long id){
        log.info("Post/resume/delete...."+id);
        resumeServiceImpl.resume_delete(id);
        return "redirect:/seeker/resume/list";
    }


    @GetMapping("/resume/my")
    public @ResponseBody ResponseEntity<List<Resume>> getMayResume(@AuthenticationPrincipal PrincipalDetails principalDetails){
         List<Resume> result = resumeServiceImpl.getMyResumes(principalDetails.getUserDto());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
