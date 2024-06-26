package com.example.jobKoreaIt.domain.offer.service;


import com.example.jobKoreaIt.domain.common.dto.UserDto;
import com.example.jobKoreaIt.domain.common.entity.User;
import com.example.jobKoreaIt.domain.common.repository.UserRepository;
import com.example.jobKoreaIt.domain.offer.dto.CompanyDto;
import com.example.jobKoreaIt.domain.offer.dto.JobOfferDto;
import com.example.jobKoreaIt.domain.offer.entity.Company;
import com.example.jobKoreaIt.domain.offer.entity.JobOffer;
import com.example.jobKoreaIt.domain.offer.repository.CompanyRepository;
import com.example.jobKoreaIt.domain.offer.repository.JobOfferRepository;
import com.example.jobKoreaIt.domain.seeker.entity.JobSeeker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@Service
@Slf4j
public class JobOfferServiceImpl {

    @Autowired
    private JobOfferRepository jobOfferRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender javaMailSender;

    @Transactional(rollbackFor = Exception.class)
    public void addJobOfferDto(UserDto userDto, JobOfferDto jobOfferDto) {

        User user = new User();
        user.setUserid(userDto.getUserid());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole("ROLE_OFFER");
        user.setCreateAt(LocalDateTime.now());
        userRepository.save(user);
        JobOffer jobOffer = new JobOffer();
        jobOffer.setUser(user);


        jobOffer.setCompanyName(jobOfferDto.getCompanyName());
        jobOffer.setZipcode(jobOfferDto.getZipcode());
        jobOffer.setCompanyNumber(jobOfferDto.getCompanyNumber());
        jobOffer.setCompanyAddr1(jobOfferDto.getCompanyAddr1());
        jobOffer.setCompanyAddr2(jobOfferDto.getCompanyAddr2());
        jobOffer.setCompanyEmail(jobOfferDto.getCompanyEmail());
        jobOffer.setCompanyPhone(jobOfferDto.getCompanyPhone());
        jobOffer.setCompanyIndustry(jobOfferDto.getCompanyIndustry());
        jobOffer.setCompanyexplanation(jobOfferDto.getCompanyexplanation());

        jobOfferRepository.save(jobOffer);
    }

    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> findUserId(String companyNumber, String companyEmail) {
        Map<String,Object> result = new LinkedHashMap();

        JobOffer jobOffer =jobOfferRepository.findByCompanyNumberAndCompanyEmail(companyNumber, companyEmail);
        if(jobOffer==null){
            result.put("message","해당 계정은 존재하지 않습니다.");
            return result;
        }
        result.put("userid",jobOffer.getUser().getUserid());
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> findUserPw(String userid, String companyNumber, String companyEmail) {

        Map<String, Object> result = new LinkedHashMap<>();
        JobOffer joboffer =jobOfferRepository.findByCompanyNumberAndCompanyEmail(companyNumber,companyEmail);
        if(joboffer==null){
            result.put("success",false);
            result.put("message","해당 계정은 존재하지 않습니다.");
            return result;
        }
        if(!StringUtils.equals(userid,joboffer.getUser().getUserid())){
            result.put("success",false);
            result.put("message","계정ID가 잘못되었습니다.");
            return result;
        }
        //메일 메시지 만들기
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(joboffer.getCompanyEmail());
        message.setSubject("[JOBKOREA] 임시패스워드 발급 ");
        //난수코드 전달로 변경
        Random rand =new Random();
        int value = (int)(rand.nextDouble()*100000) ;

        message.setText(value+"");
        javaMailSender.send(message);

        User user = joboffer.getUser();
        user.setPassword(passwordEncoder.encode(value+""));
        userRepository.save(user);
        joboffer.setUser(user);
        jobOfferRepository.save(joboffer);
        result.put("message","이메일로 임시 패스워드 전달 완료");
        result.put("success",true);
        return result;
    }
    //-----------------------
    //박정현 Company CRUD
    //-----------------------
    @Autowired
    private CompanyRepository companyRepository;

    @Transactional(rollbackFor = Exception.class)
    public void CompanyRegistration(CompanyDto companyDto){
        log.info("회사등록...");
        Company company = new Company();
        company.setCompanyName(companyDto.getCompanyName());
        company.setCompanyNumber(companyDto.getCompanyNumber());
        company.setCompanyAddr1(companyDto.getCompanyAddr1());
        company.setCompanyEmail(companyDto.getCompanyEmail());
        company.setCompanyPhone(companyDto.getCompanyPhone());
        company.setCompanyIndustry(companyDto.getCompanyIndustry());
        company.setCompanyexplanation(companyDto.getCompanyexplanation());
        companyRepository.save(company);
    }
    @Transactional(rollbackFor = Exception.class)
    public void CompanyUpdate(Company company){
        log.info("회사정보수정...");
        Company existingCompany = companyRepository.findById(company.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid company Id:" + company.getId()));

        existingCompany.setCompanyName(company.getCompanyName());
        existingCompany.setCompanyNumber(company.getCompanyNumber());
        existingCompany.setZipcode(company.getZipcode());
        existingCompany.setCompanyAddr1(company.getCompanyAddr1());
        existingCompany.setCompanyAddr2(company.getCompanyAddr2());
        existingCompany.setCompanyEmail(company.getCompanyEmail());
        existingCompany.setCompanyPhone(company.getCompanyPhone());
        existingCompany.setCompanyIndustry(company.getCompanyIndustry());
        existingCompany.setCompanyexplanation(company.getCompanyexplanation());

        companyRepository.save(existingCompany);

    }
    @Transactional(rollbackFor = Exception.class)
    public void RemoveCompany(Long id){
        log.info("회사정보 삭제...");
        companyRepository.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public Company showCompany() {
        log.info("회사정보 조회...");
        return companyRepository.findById(1L).orElseThrow(() -> new RuntimeException("Company not found"));
    }
}
