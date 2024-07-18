package com.example.jobKoreaIt.domain.seeker.service;

import com.example.jobKoreaIt.config.auth.PrincipalDetails;
import com.example.jobKoreaIt.domain.common.dto.UserDto;
import com.example.jobKoreaIt.domain.common.entity.User;
import com.example.jobKoreaIt.domain.common.repository.UserRepository;
import com.example.jobKoreaIt.domain.offer.dto.JobOfferDto;
import com.example.jobKoreaIt.domain.offer.entity.JobOffer;
import com.example.jobKoreaIt.domain.offer.entity.Recruit;
import com.example.jobKoreaIt.domain.offer.repository.JobOfferRepository;
import com.example.jobKoreaIt.domain.offer.repository.RecruitRepository;
import com.example.jobKoreaIt.domain.seeker.dto.ApplyDto;
import com.example.jobKoreaIt.domain.seeker.dto.JobSeekerDto;
import com.example.jobKoreaIt.domain.seeker.entity.Apply;
import com.example.jobKoreaIt.domain.seeker.entity.JobSeeker;
import com.example.jobKoreaIt.domain.seeker.entity.Resume;
import com.example.jobKoreaIt.domain.seeker.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Slf4j
public class ApplyServiceImpl {
    @Autowired
    ApplyRepository applyRepository;
    @Autowired
    ResumeRepository resumeRepository;
    @Autowired
    JobSeekerRepository jobSeekerRepository;

    @Autowired
    RecruitRepository recruitRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JobOfferRepository jobOfferRepository;

    @Autowired
    CareerRepository careerRepository;

    @Autowired
    CertificationRepository certificationRepository;


//    @Transactional(rollbackFor = Exception.class)
//    public Apply apply_add(ApplyDto applyDto, Optional<Resume> resume){
//        log.info("ApplyServiceImpl/apply_add..");
//        log.info("applyDto.. : "+applyDto);
//        log.info("resume : "+resume);
//
//        Apply apply=new Apply();
//        //resume에 있는 id를 받아온다.
//        Optional<Resume> resume1=resumeRepository.findById(resume.get().getId());
//        //JobSeeker에있는 username으로 찾아온다.
//        JobSeeker JobSeeker = jobSeekerRepository.findByTel(applyDto.getTell());
//        log.info("jobSeeker : "+JobSeeker);
//
//        apply.setMilitaryService(applyDto.getMilitaryService());
//        apply.setNationality(applyDto.getNationality());
//        apply.setObjective(applyDto.getObjective());
//        apply.setExpectedSalary(applyDto.getExpectedSalary());
//        apply.setApply_id(applyDto.getId());
//        apply.setResume(resume1.get());
//        apply.setJobSeeker(JobSeeker);
//
//        log.info("apply : "+apply);
//        return  applyRepository.save(apply);
//    }
//
//    //입사지원서 리스트
//    @Transactional(rollbackFor = Exception.class)
//    public List<ApplyDto> apply_list() {
//        log.info("ApplyServiceImpl/apply_list...!");
//        List<Apply> applyList = applyRepository.findAll();
//        return applyList.stream()
//                .map(this::applyDtoList)
//                .collect(Collectors.toList());
//    }
//
//    //필요한 정보만을 꺼내서 dto에 넣어준다.
//    public ApplyDto applyDtoList(Apply apply){
//        log.info("ApplyDtoList...필요한 정보를 꺼내온다");
//        List<Apply> apply1 = applyRepository.findAll();
//        log.info("apply : "+ apply1);
//
//        ApplyDto dto=new ApplyDto();
//
//        dto.setId(apply.getApply_id());
//
//        JobSeeker jobSeeker=apply.getJobSeeker();
//        Resume resume=apply.getResume();
//        log.info("jobSeeker : "+jobSeeker);
//        log.info("resume : "+resume);
//
//        dto.setName(jobSeeker.getUsername());
//        //!!!!!!!!!!!!!!!!!
//        dto.setTell(jobSeeker.getTel());
//        dto.setObjective(apply.getObjective());
//        return dto;
//    }

    @Transactional(rollbackFor = Exception.class)
    public boolean apply_add(Long recruit_id, Long resume_id) {

        Optional<Recruit> recruitOptional =  recruitRepository.findById(recruit_id);

        if(recruitOptional.isEmpty())
            return false;

        Optional<Resume> resumeOptional =  resumeRepository.findById(resume_id);
        if(resumeOptional.isEmpty())
            return false;

        Apply apply = new Apply();
        apply.setResume(resumeOptional.get());
        apply.setRecruit(recruitOptional.get());

        apply.setOffer_status("");
        apply.setSeeker_status("이력서 접수완료");
        apply.setOffer_status("면접 대기");

        applyRepository.save(apply);

        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public  List<Apply> getOfferApply(Long recruit_id) {



        Optional<Recruit> recruitOptional = recruitRepository.findById(recruit_id);

        if(recruitOptional.isEmpty())
            return null;

        //채용공고에 지원한 사원들 리스트
        List<Apply> replyList = applyRepository.findAllByRecruit(recruitOptional.get());


        Map<List<Apply>,Object> Apply_carrer = new LinkedHashMap<>();


        return replyList ;
    }
    @Transactional(rollbackFor = Exception.class)
    public Apply getStatusByResumeId(Long resumeId) {

        Resume resume = resumeRepository.findById(resumeId).get();

        return applyRepository.findByResume(resume);
    }



    @Transactional(rollbackFor = Exception.class)

    public void changeStatus(ApplyDto applyDto, String offerStatus, String seekerStatus) {
        Resume resume = resumeRepository.findById(applyDto.getResume_id()).get();
        Recruit recruit = recruitRepository.findById(applyDto.getRecruit_id()).get();

        Apply apply =  applyRepository.findByResumeAndRecruit(resume,recruit);
        apply.setSeeker_status(seekerStatus);
        apply.setOffer_status(offerStatus);
        applyRepository.save(apply);
    }
    @Transactional(rollbackFor = Exception.class)

    public List<Apply> getSeekerApply(PrincipalDetails principalDetails) {
        UserDto userDto = principalDetails.getUserDto();
        JobSeekerDto jobSeekerDto = principalDetails.getJobSeekerDto();

        User user =  userRepository.findById(userDto.getUserid()).get();

        List<Resume> resumeList =  resumeRepository.findAllByUser(user);
        List<Apply> applyList = new ArrayList<>();

        resumeList.forEach(resume->{
            Apply apply =  applyRepository.findByResume(resume);
            if(apply!=null)
                applyList.add(apply);
        });

        return applyList;
    }
}
