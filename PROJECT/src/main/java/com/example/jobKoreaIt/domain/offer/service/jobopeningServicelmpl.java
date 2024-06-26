package com.example.jobKoreaIt.domain.offer.service;

import com.example.jobKoreaIt.domain.offer.dto.RecruitDto;
import com.example.jobKoreaIt.domain.offer.entity.Recruit;
import com.example.jobKoreaIt.domain.offer.repository.JobopeningRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class jobopeningServicelmpl {

    @Autowired
    private JobopeningRepository jobopeningRepository;


    @Transactional(rollbackFor = Exception.class)
    public void jobopenadd(RecruitDto recruitDto){
        log.info("공고등록...");
        Recruit recruit = new Recruit();
        recruit.setTitle(recruitDto.getTitle());
        recruit.setCareer(recruitDto.getCareer());
        recruit.setAbility(recruitDto.getAbility());
        recruit.setJobwork(recruitDto.getJobwork());
        recruit.setMoney(recruitDto.getMoney());
        recruit.setJobzone(recruitDto.getJobzone());
        recruit.setJobspecial(recruitDto.getJobspecial());
        recruit.setWelfare(recruitDto.getWelfare());
        recruit.setJobplace(recruitDto.getJobplace());
        recruit.setTime(recruitDto.getTime());
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
    public void jobopenupdate(Recruit recruit) {
        Recruit existingRecruit = jobopeningRepository.findById(recruit.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid company Id:" + recruit.getId()));
        existingRecruit.setTitle(recruit.getTitle());
        existingRecruit.setCareer(recruit.getCareer());
        existingRecruit.setAbility(recruit.getAbility());
        existingRecruit.setJobwork(recruit.getJobwork());
        existingRecruit.setMoney(recruit.getMoney());
        existingRecruit.setJobzone(recruit.getJobzone());
        existingRecruit.setWelfare(recruit.getWelfare());
        existingRecruit.setJobplace(recruit.getJobplace());
        existingRecruit.setTime(recruit.getTime());
        existingRecruit.setJobway(recruit.getJobway());

        jobopeningRepository.save(existingRecruit);
    }
}
