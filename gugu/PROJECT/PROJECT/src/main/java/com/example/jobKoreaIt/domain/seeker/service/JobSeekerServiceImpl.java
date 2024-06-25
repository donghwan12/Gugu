package com.example.jobKoreaIt.domain.seeker.service;


import com.example.jobKoreaIt.config.auth.jwt.TokenInfo;
import com.example.jobKoreaIt.domain.common.dto.UserDto;
import com.example.jobKoreaIt.domain.common.entity.User;
import com.example.jobKoreaIt.domain.common.repository.UserRepository;
import com.example.jobKoreaIt.domain.offer.entity.JobOffer;
import com.example.jobKoreaIt.domain.seeker.dto.JobSeekerDto;
import com.example.jobKoreaIt.domain.seeker.entity.JobSeeker;
import com.example.jobKoreaIt.domain.seeker.repository.JobSeekerRepository;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

@Service
@Slf4j
public class JobSeekerServiceImpl {

    @Autowired
    private JobSeekerRepository jobSeekerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private JavaMailSender javaMailSender;


    @Transactional(rollbackFor = Exception.class)
    public void addJobSeeker(UserDto userDto, JobSeekerDto jobSeekerDto){

        User user = new User();
        user.setUserid(userDto.getUserid());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole("ROLE_SEEKER");
        user.setCreateAt(LocalDateTime.now());
        userRepository.save(user);

        JobSeeker jobSeeker = new JobSeeker();
        jobSeeker.setUser(user);
        jobSeeker.setEmail(jobSeekerDto.getEmail());
        jobSeeker.setUsername(jobSeekerDto.getUsername());
        jobSeeker.setZipcode(jobSeekerDto.getZipcode());
        jobSeeker.setTel(jobSeekerDto.getTel());
        jobSeeker.setAddr1(jobSeekerDto.getAddr1());
        jobSeeker.setAddr2(jobSeekerDto.getAddr2());
        jobSeekerRepository.save(jobSeeker);

    }
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> findUserId(String username, String tel) {
        Map<String,Object> result = new LinkedHashMap();

        JobSeeker jobSeeker =jobSeekerRepository.findByUsernameAndTel(username, tel);
        if(jobSeeker==null){
            result.put("message","해당 계정은 존재하지 않습니다.");
            return result;
        }
        result.put("userid",jobSeeker.getUser().getUserid());

        return result;

    }
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> findUserPw(String userid, String username, String tel) {

        Map<String, Object> result = new LinkedHashMap<>();
        JobSeeker jobSeeker =jobSeekerRepository.findByUsernameAndTel(username,tel);
        if(jobSeeker==null){
            result.put("success",false);
            result.put("message","해당 계정은 존재하지 않습니다.");
            return result;
        }
        if(!StringUtils.equals(userid,jobSeeker.getUser().getUserid())){
            result.put("success",false);
            result.put("message","계정ID가 잘못되었습니다.");
            return result;
        }
        //메일 메시지 만들기
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(jobSeeker.getEmail());
        message.setSubject("[JOBKOREA] 임시패스워드 발급 ");
        //난수코드 전달로 변경
        Random rand =new Random();
        int value = (int)(rand.nextDouble()*100000) ;

        message.setText(value+"");
        javaMailSender.send(message);

        User user = jobSeeker.getUser();
        user.setPassword(passwordEncoder.encode(value+""));
        userRepository.save(user);
        jobSeeker.setUser(user);
        jobSeekerRepository.save(jobSeeker);
        result.put("message","이메일로 임시 패스워드 전달 완료");
        result.put("success",true);
        return result;
    }
}
