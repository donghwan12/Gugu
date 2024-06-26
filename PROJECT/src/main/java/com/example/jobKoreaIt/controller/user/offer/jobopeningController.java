package com.example.jobKoreaIt.controller.user.offer;

import com.example.jobKoreaIt.domain.offer.dto.RecruitDto;
import com.example.jobKoreaIt.domain.offer.entity.Company;
import com.example.jobKoreaIt.domain.offer.entity.Recruit;
import com.example.jobKoreaIt.domain.offer.service.JobOfferServiceImpl;
import com.example.jobKoreaIt.domain.offer.service.jobopeningServicelmpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@Slf4j
@RequestMapping("/offer")
public class jobopeningController {

    @Autowired
    private JobOfferServiceImpl jobOfferService;

    @Autowired
    private jobopeningServicelmpl jobopeningServicelmpl;


    @GetMapping("/jobopening/add")
    public void jobadd() {
        log.info("채용공고 등록...");
    }

    @PostMapping("/jobopening/add")
    public void jobaddPost(RecruitDto recruitDto) {
        log.info("채용공고 등록중...");
        jobopeningServicelmpl.jobopenadd(recruitDto);

    }

    @GetMapping("/jobopening/read")
    public void jobread(Model model) {
        log.info("채용공고 조회...");

        Company company = jobOfferService.showCompany();
        Recruit recruit = jobopeningServicelmpl.jobopenRead();

        model.addAttribute("career" , recruit.getCareer());
        model.addAttribute("money",recruit.getMoney());
        model.addAttribute("ability",recruit.getAbility());
        model.addAttribute("jobzone",recruit.getJobzone());
        model.addAttribute("jobwork",recruit.getJobwork());
        model.addAttribute("jobspecial",recruit.getJobspecial());

        model.addAttribute("jobpapers",recruit.getJobpapers());


        model.addAttribute("companyName", company.getCompanyName());
        model.addAttribute("companyaddr", company.getCompanyAddr1());
        model.addAttribute("companyEmail", company.getCompanyEmail());
        model.addAttribute("companyPhone", company.getCompanyPhone());
        model.addAttribute("companyIndustry", company.getCompanyIndustry());
        model.addAttribute("companyexplanation",company.getCompanyexplanation());
    }

    @GetMapping("/jobopening/delete")
    public void jobdelete() {
        log.info("채용공고 삭제...");
    }

    @PostMapping("/jobopening/delete")
    public String jobdelete(@RequestParam("id") Long id) {
        log.info("채용공고 삭제중...");
        jobopeningServicelmpl.jobopenRemove(id);
        return "redirect:/offer/jobopening/read";
    }

    @GetMapping("/jobopening/update")
    public void jobupdate() {
        log.info("채용공고 수정...");
    }
    
    @PostMapping("/jobopening/update")
    public void jobupdatePost(
            @RequestParam("id") Long id,
            @RequestParam("title") String title,
            @RequestParam("career") String career,
            @RequestParam("ability") String ability,
            @RequestParam("jobwork") String jobwork,
            @RequestParam("money") String money,
            @RequestParam("jobzone") String jobzone,
            @RequestParam("welfare") String welfare,
            @RequestParam("jobplace") String jobplace,
            @RequestParam("time") LocalDate time,
            @RequestParam("jobway") String jobway
    ) {
        log.info("채용공고 수정...");

        Recruit recruit = new Recruit();
        recruit.setId(id);
        recruit.setTitle(title);
        recruit.setCareer(career);
        recruit.setAbility(ability);
        recruit.setJobwork(jobwork);
        recruit.setMoney(money);
        recruit.setJobzone(jobzone);
        recruit.setWelfare(welfare);
        recruit.setJobplace(jobplace);
        recruit.setTime(time);
        recruit.setJobway(jobway);

        jobopeningServicelmpl.jobopenupdate(recruit);
    }
    
}
