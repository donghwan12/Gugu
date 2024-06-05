package com.example.jobKoreaIt.domain.seeker.service;

import com.example.jobKoreaIt.domain.seeker.dto.ResumeDto;
import com.example.jobKoreaIt.domain.seeker.entity.Career;
import com.example.jobKoreaIt.domain.seeker.entity.Resume;
import com.example.jobKoreaIt.domain.seeker.dto.ResumeFormDto;
import com.example.jobKoreaIt.domain.seeker.repository.CareerRepository;
import com.example.jobKoreaIt.domain.seeker.repository.ResumeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class JobSeekerServiceImpl {

    @Autowired
    public void JobSeekerServiceImpl(ResumeRepository resumeRepository, CareerRepository careerRepository) {
        this.resumeRepository = resumeRepository;
        this.careerRepository = careerRepository;
    }

    @Autowired
    private ResumeRepository resumeRepository;
    private CareerRepository careerRepository;

    private ResumeFormDto resumeForm=new ResumeFormDto();

    @Transactional(rollbackFor = Exception.class)
    public void resume_add(ResumeFormDto resumeForm) {
        log.info("JobSeekerRepository/resume_add...!");
        Resume resume = resumeForm.getResume();
        List<Career> ResumeFormDto = List.of();
        List<Career> careers = ResumeFormDto;
        for (Career career : careers) {
            career.setResume(resume);
        }
        resume.setCareers(careers);
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

    //이력서 삭제 서비스
    @Transactional(rollbackFor = Exception.class)
    public void resume_delete(long id){
        log.info("resume_delete invoke...",+id);
        Optional<Resume> optionalResume = resumeRepository.findById(id);
        if (optionalResume.isPresent()) {
            Resume resume = optionalResume.get();
            resumeRepository.delete(resume);
            log.info("Resume deleted successfully");
        } else {
            log.info("Resume with id {} not found", id);
        }
    }


    @Transactional(rollbackFor = Exception.class)
    public void resume_update(){
        resumeRepository.findAll();

        log.info("TEST...");
    }


}
