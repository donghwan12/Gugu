package com.example.jobKoreaIt.domain.offer.service;

import com.example.jobKoreaIt.domain.offer.dto.JobOfferDto;
import com.example.jobKoreaIt.domain.offer.dto.RecruitDto;
import com.example.jobKoreaIt.domain.offer.entity.JobOffer;
import com.example.jobKoreaIt.domain.offer.entity.Recruit;
import com.example.jobKoreaIt.domain.offer.repository.JobOfferRepository;
import com.example.jobKoreaIt.domain.offer.repository.JobopeningRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class jobopeningServicelmpl {

    @Autowired
    private JobopeningRepository jobopeningRepository;
    @Autowired
    private JobOfferRepository jobOfferRepository;

    @Transactional(rollbackFor = Exception.class)
    public void jobopenadd(RecruitDto recruitDto, JobOfferDto jobOfferDto){
        log.info("공고등록...");
        Optional<JobOffer> jobOfferOptional =  jobOfferRepository.findByCompanyNumber(jobOfferDto.getCompanyNumber());
        if(jobOfferOptional.isEmpty())
            return ;

        JobOffer jobOffer = jobOfferOptional.get();

        Recruit recruit = new Recruit();
        recruit.setJobOffer(jobOffer);
        recruit.setRecuitStatus(recruitDto.getRecuitStatus());
        recruit.setTitle(recruitDto.getTitle());
        recruit.setCareer(recruitDto.getCareer());
        recruit.setAbility(recruitDto.getAbility());
        recruit.setJobzone(recruitDto.getJobzone());
        recruit.setDepartment(recruitDto.getDepartment());
        recruit.setJobwork(recruitDto.getJobwork());
        recruit.setSalary(recruitDto.getSalary());

        recruit.setJobspecial(recruitDto.getJobspecial());
        recruit.setWelfare(recruitDto.getWelfare());

        recruit.setStartTime(recruitDto.getStartTime());
        recruit.setEndTime(recruitDto.getEndTime());

        recruit.setJobway(recruitDto.getJobway());
        recruit.setJobpapers(recruitDto.getJobpapers());

        jobopeningRepository.save(recruit);


    }

    @Transactional(rollbackFor = Exception.class)
    public void jobopenRemove(Long id){
        log.info("공고 삭제...");
        jobopeningRepository.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public Recruit jobopenRead() {
        log.info("공고 조회...");
        return jobopeningRepository.findById(1L).orElseThrow(() -> new RuntimeException("Company not found"));
    }

    @Transactional(rollbackFor = Exception.class)
    public void jobopenupdate(RecruitDto recruitDto) {

        Recruit existingRecruit = jobopeningRepository.findById(recruitDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid company Id:" + recruitDto.getId()));


        existingRecruit.setTitle(recruitDto.getTitle());
        existingRecruit.setCareer(recruitDto.getCareer());
        existingRecruit.setRecuitStatus(recruitDto.getRecuitStatus());
        existingRecruit.setDepartment(recruitDto.getDepartment());
        existingRecruit.setAbility(recruitDto.getAbility());
        existingRecruit.setJobwork(recruitDto.getJobwork());
        existingRecruit.setJobzone(recruitDto.getJobzone());
        existingRecruit.setDetailAddress(recruitDto.getDetailAddress());
        existingRecruit.setSalary(recruitDto.getSalary());
        existingRecruit.setSalary(recruitDto.getSalary());
        existingRecruit.setJobspecial(recruitDto.getJobspecial());
        existingRecruit.setWelfare(recruitDto.getWelfare());
        existingRecruit.setStartTime(recruitDto.getStartTime());
        existingRecruit.setEndTime(recruitDto.getEndTime());
        existingRecruit.setJobway(recruitDto.getJobway());
        existingRecruit.setJobpapers(recruitDto.getJobpapers());
        //파일경로는 나중에
        existingRecruit.setFilePath(null);
        jobopeningRepository.save(existingRecruit);
    }


    @Transactional(rollbackFor = Exception.class)
    public List<Recruit> getMyRecruit(JobOfferDto dto) {
        Optional<JobOffer> jobOfferOptional =  jobOfferRepository.findByCompanyNumber(dto.getCompanyNumber());

        if(jobOfferOptional.isEmpty()) {
            System.out.println("일치하는 계정없습니다.");
            return null;
        }
        System.out.println("JobOffer " + jobOfferOptional.get());

        JobOffer jobOffer = jobOfferOptional.get();
        List<Recruit> list = jobopeningRepository.findAllByJobOfferOrderByIdDesc(jobOffer);
        System.out.println("LIST : " + list);
        return list;
    }
    @Transactional(rollbackFor = Exception.class)
    public Recruit getMyRecruitOne(Long id) {
         return jobopeningRepository.findById(id).get();
    }
}
