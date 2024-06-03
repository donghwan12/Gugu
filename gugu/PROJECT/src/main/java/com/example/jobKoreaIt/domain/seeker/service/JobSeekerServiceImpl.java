package com.example.jobKoreaIt.domain.seeker.service;



import com.example.jobKoreaIt.domain.seeker.entity.Resume;
import com.example.jobKoreaIt.domain.seeker.repository.JobSeekerRepository;
import com.example.jobKoreaIt.domain.seeker.repository.ResumeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class JobSeekerServiceImpl {

    @Autowired
    private JobSeekerRepository jobSeekerRepository;



    @Transactional(rollbackFor = Exception.class)
    public void function(){
        log.info("TEST...");
    }


    //------------------
    //이력서 CRUD (이동환)
    //------------------
    @Autowired
    private ResumeRepository resumeRepository;

    @Transactional(rollbackFor = Exception.class)
    public void resume_add(Resume resume){

        log.info("JobSeekerRepository/resume_add...!");
        resumeRepository.save(resume);
    }

    @Transactional(rollbackFor = Exception.class)
    public List<Resume> resume_list(){
        log.info("JobSeekerRepositroy/resumeListAll...!");
        return  resumeRepository.findAll();
    }

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
        log.info("TEST...");
    }

}
