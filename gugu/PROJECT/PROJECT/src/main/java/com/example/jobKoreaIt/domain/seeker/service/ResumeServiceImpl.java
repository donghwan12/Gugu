package com.example.jobKoreaIt.domain.seeker.service;

import com.example.jobKoreaIt.domain.common.repository.UserRepository;
import com.example.jobKoreaIt.domain.seeker.dto.JobSeekerDto;
import com.example.jobKoreaIt.domain.seeker.dto.ResumeDto;
import com.example.jobKoreaIt.domain.seeker.entity.Career;
import com.example.jobKoreaIt.domain.seeker.entity.Resume;
import com.example.jobKoreaIt.domain.seeker.dto.ResumeFormDto;
import com.example.jobKoreaIt.domain.seeker.repository.CareerRepository;
import com.example.jobKoreaIt.domain.seeker.repository.JobSeekerRepository;
import com.example.jobKoreaIt.domain.seeker.repository.ResumeRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Service
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class ResumeServiceImpl {

    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ResumeRepository resumeRepository;
    private CareerRepository careerRepository;

    ResumeFormDto resumeForm;

    @Autowired
    public void JobSeekerServiceImpl(ResumeRepository resumeRepository, CareerRepository careerRepository) {
        this.resumeRepository = resumeRepository;
        this.careerRepository = careerRepository;
        this.resumeForm = new ResumeFormDto();
    }



    @Transactional(rollbackFor = Exception.class)
    public void resume_add(ResumeFormDto resumeForm) {
        log.info("JobSeekerResumeRepository/resume_add...!");
        log.info("Received careers in service: " + resumeForm.getCareers());

        // Resume 객체를 가져와서 저장
        Resume resume = resumeForm.getResume();

        // 각 Career 객체에 Resume 객체를 설정
        List<Career> careers = resumeForm.getCareers();
        if (careers != null) {
            // CopyOnWriteArrayList 사용
            List<Career> careersToSave = new CopyOnWriteArrayList<>(careers);
            log.info("Original careers list size: " + careers.size());
            log.info("Copied careers list size: " + careersToSave.size());

            for (Career career : careersToSave) {
                log.info("Setting resume for career: " + career);
                career.setResume(resume);
            }

            // Resume 객체에 Careers 리스트 설정
            resume.setCareers(careersToSave);

            // Resume 저장
            log.info("Saving resume: " + resume);
            resumeRepository.save(resume);

            // 각 Career 객체 저장 (CascadeType.ALL을 사용하여 자동으로 저장되도록 할 수도 있습니다)
            for (Career career : careersToSave) {
                log.info("Saving career: " + career);
                careerRepository.save(career);
            }
        } else {
            // Resume 저장 (Careers가 없는 경우)
            log.info("Saving resume without careers: " + resume);
            resumeRepository.save(resume);
        }
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
            careerRepository.deleteAllByResume(resume);
            log.info("이력서 삭제성공");
        } else {
            log.info("이력서 삭제 실패!", id);
        }
    }


    @Transactional(rollbackFor = Exception.class)
    public void resume_update(long id, ResumeFormDto updatedResume) {
        // 이력서 업데이트 처리
        Optional<Resume> optionalResume = resumeRepository.findById(id);

        if (optionalResume.isPresent()) {
            Resume resume = optionalResume.get();
            Resume updatedResumeDto = updatedResume.getResume();
            System.out.println("updatedResumeDto : "+ updatedResumeDto);

            // 수정된 내용 업데이트
            resume.setName(updatedResumeDto.getName());
            resume.setEmail(updatedResumeDto.getEmail());
            resume.setPhone(updatedResumeDto.getPhone());
            resume.setSchoolName(updatedResumeDto.getSchoolName());
            resume.setMajor(updatedResumeDto.getMajor());
            resume.setGraduationYear(updatedResumeDto.getGraduationYear());
            resume.setSummary(updatedResumeDto.getSummary());
            resume.setHobbies(updatedResumeDto.getHobbies());

            // 기존 경력 사항 업데이트
            List<Career> existingCareers = resume.getCareers();
            System.out.println("existingCareers : "+existingCareers);
            List<Career> updatedCareers = updatedResume.getCareers();
            System.out.println("updatedCareers : "+updatedCareers);

            int minSize = Math.min(existingCareers.size(), updatedCareers.size());
            for (int i = 0; i < minSize; i++) {
                Career existingCareer = existingCareers.get(i);
                Career updatedCareer = updatedCareers.get(i);
                existingCareer.setCompanyName(updatedCareer.getCompanyName());
                existingCareer.setPosition(updatedCareer.getPosition());
                existingCareer.setStartDate(updatedCareer.getStartDate());
                existingCareer.setEndDate(updatedCareer.getEndDate());
            }

            // 새 경력 사항 추가
            if (updatedCareers.size() > existingCareers.size()) {
                for (int i = existingCareers.size(); i < updatedCareers.size(); i++) {
                    resume.addCareer(updatedCareers.get(i));
                }
            }

            log.info("resume : " + resume);
            log.info("getCareers : " + resume.getCareers());

            // 수정된 이력서 저장
            resumeRepository.save(resume);
            log.info("Resume with id {} updated successfully", id);
        } else {
            log.info("Resume with id {} not found", id);
        }
    }


}




