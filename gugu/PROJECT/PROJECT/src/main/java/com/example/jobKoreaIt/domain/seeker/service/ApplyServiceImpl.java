package com.example.jobKoreaIt.domain.seeker.service;

import com.example.jobKoreaIt.domain.seeker.dto.ApplyDto;
import com.example.jobKoreaIt.domain.seeker.entity.Apply;
import com.example.jobKoreaIt.domain.seeker.entity.JobSeeker;
import com.example.jobKoreaIt.domain.seeker.entity.Resume;
import com.example.jobKoreaIt.domain.seeker.repository.ApplyRepository;
import com.example.jobKoreaIt.domain.seeker.repository.JobSeekerRepository;
import com.example.jobKoreaIt.domain.seeker.repository.ResumeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ApplyServiceImpl {
    @Autowired
    ApplyRepository applyRepository;
    @Autowired
    ResumeRepository resumeRepository;
    @Autowired
    JobSeekerRepository jobSeekerRepository;

    public Apply apply_add(ApplyDto applyDto, Optional<Resume> resume){
        log.info("ApplyServiceImpl/apply_add..");
        log.info("applyDto.. : "+applyDto);
        log.info("resume : "+resume);

        Apply apply=new Apply();
        //resume에 있는 id를 받아온다.
        Optional<Resume> resume1=resumeRepository.findById(resume.get().getId());
        //JobSeeker에있는 username으로 찾아온다.
        JobSeeker JobSeeker = jobSeekerRepository.findByTel(applyDto.getTell());
        log.info("jobSeeker : "+JobSeeker);

        apply.setMilitaryService(applyDto.getMilitaryService());
        apply.setNationality(applyDto.getNationality());
        apply.setObjective(applyDto.getObjective());
        apply.setExpectedSalary(applyDto.getExpectedSalary());
        apply.setApply_id(applyDto.getId());
        apply.setResume(resume1.get());
        apply.setJobSeeker(JobSeeker);

        log.info("apply : "+apply);
        return  applyRepository.save(apply);
    }
}
