package com.example.jobKoreaIt.domain.seeker.service;

import com.example.jobKoreaIt.domain.seeker.dto.ResumeDto;
import com.example.jobKoreaIt.domain.seeker.entity.Resume;
import com.example.jobKoreaIt.domain.seeker.repository.ResumeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class JobSeekerServiceImpl {

    @Autowired
    private ResumeRepository resumeRepository;

    @Transactional(rollbackFor = Exception.class)
    public void resume_add(Resume resume){
        log.info("JobSeekerRepository/resume_add...!");
        resumeRepository.save(resume);
    }

    @Transactional(rollbackFor = Exception.class)
    public List<ResumeDto> resume_list(){
        log.info("JobSeekerRepositroy/resumeListAll...!");
        List<Resume> resumeList = resumeRepository.findAll();
        return resumeList.stream()
                .map(this::resume_dto_list) // Resume를 ResumeDto로 변환
                .collect(Collectors.toList());
    }

    // 필요한 정보만을 꺼내서 dto에 넣어준다
    public ResumeDto resume_dto_list(Resume resume){

        log.info("Resume_dto_list,,,!");
        ResumeDto dto = new ResumeDto();
        dto.setId(resume.getId());
        dto.setEmail(resume.getEmail());
        dto.setName(resume.getName());
        dto.setPhone(resume.getPhone());
        dto.setCreationDate(resume.getCreationDate().toLocalDate());
        return dto;
    }

    //이력서 상세보기위한 서비스
    @Transactional(rollbackFor = Exception.class)
    public Optional<Resume> resume_read(Long id)
    {
        log.info("JobSeekerRepositroy/read...!");
        return resumeRepository.findById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void resume_delete(){
        log.info("TEST...");
    }


    @Transactional(rollbackFor = Exception.class)
    public void resume_update(){
        resumeRepository.findAll();

        log.info("TEST...");
    }
}
