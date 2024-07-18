package com.example.jobKoreaIt.domain.seeker.service;

import com.example.jobKoreaIt.domain.common.dto.UserDto;
import com.example.jobKoreaIt.domain.common.entity.User;
import com.example.jobKoreaIt.domain.common.repository.UserRepository;
import com.example.jobKoreaIt.domain.seeker.dto.*;
import com.example.jobKoreaIt.domain.seeker.entity.Carrer;
import com.example.jobKoreaIt.domain.seeker.entity.Certification;
import com.example.jobKoreaIt.domain.seeker.entity.JobSeeker;
import com.example.jobKoreaIt.domain.seeker.entity.Resume;
import com.example.jobKoreaIt.domain.seeker.repository.CareerRepository;
import com.example.jobKoreaIt.domain.seeker.repository.CertificationRepository;
import com.example.jobKoreaIt.domain.seeker.repository.JobSeekerRepository;
import com.example.jobKoreaIt.domain.seeker.repository.ResumeRepository;
import com.example.jobKoreaIt.properties.UPLOADPATH;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
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

    @Autowired
    private CareerRepository careerRepository;

    @Autowired
    private CertificationRepository certificationRepository;





    @Transactional(rollbackFor = Exception.class)
    public void resume_add(ResumeFormDto resumeForm) {
        log.info("JobSeekerResumeRepository/resume_add...!");
        log.info("Received careers in service: " + resumeForm.getCareers());

        // Resume 객체를 가져와서 저장
        Resume resume = resumeForm.getResume();

        // 각 Career 객체에 Resume 객체를 설정
        List<Carrer> careers = resumeForm.getCareers();
        if (careers != null) {
            // CopyOnWriteArrayList 사용
            List<Carrer> careersToSave = new CopyOnWriteArrayList<>(careers);
            log.info("Original careers list size: " + careers.size());
            log.info("Copied careers list size: " + careersToSave.size());

            for (Carrer career : careersToSave) {
                log.info("Setting resume for career: " + career);
                career.setResume(resume);
            }

            // Resume 객체에 Careers 리스트 설정
            //resume.setCareers(careersToSave);

            // Resume 저장
            log.info("Saving resume: " + resume);
            resumeRepository.save(resume);

            // 각 Career 객체 저장 (CascadeType.ALL을 사용하여 자동으로 저장되도록 할 수도 있습니다)
            for (Carrer career : careersToSave) {
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
        //!!!!!!!!!!
        //dto.setCreationDate(resume.getCreationDate().toLocalDate());
        return dto;
    }

    //이력서 상세보기위한 서비스
    @Transactional(rollbackFor = Exception.class)
    public Map<String,Object> resume_read(Long id)
    {
        Map<String,Object> result = new HashMap();

        log.info("JobSeekerRepositroy/read...!");
        Resume resume = resumeRepository.findById(id).get();
        List<Carrer> carrerList =  careerRepository.findAllByResume(resume);
        List<Certification> certificationList = certificationRepository.findAllByResume(resume);

        result.put("resume", resume);
        result.put("carrerList", carrerList);
        result.put("certificationList", certificationList);

        return result;
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

//        if (optionalResume.isPresent()) {
//            Resume resume = optionalResume.get();
//            Resume updatedResumeDto = updatedResume.getResume();
//            System.out.println("updatedResumeDto : "+ updatedResumeDto);
//
//            // 수정된 내용 업데이트
//            //!!!!!!!!!!
//            resume.setSchoolName(updatedResumeDto.getSchoolName());
//            resume.setMajor(updatedResumeDto.getMajor());
//            resume.setGraduationYear(updatedResumeDto.getGraduationYear());
//            resume.setSummary(updatedResumeDto.getSummary());
//            resume.setHobbies(updatedResumeDto.getHobbies());
//
//            // 기존 경력 사항 업데이트
//           // List<Carrer> existingCareers = resume.getCareers();
//           // System.out.println("existingCareers : "+existingCareers);
//            List<Carrer> updatedCareers = updatedResume.getCareers();
//            System.out.println("updatedCareers : "+updatedCareers);

            //int minSize = Math.min(existingCareers.size(), updatedCareers.size());
//            for (int i = 0; i < minSize; i++) {
//                Carrer existingCareer = existingCareers.get(i);
//                Carrer updatedCareer = updatedCareers.get(i);
//                existingCareer.setCompanyName(updatedCareer.getCompanyName());
//                existingCareer.setPosition(updatedCareer.getPosition());
//                existingCareer.setStartDate(updatedCareer.getStartDate());
//                existingCareer.setEndDate(updatedCareer.getEndDate());
//            }

//            // 새 경력 사항 추가
//            if (updatedCareers.size() > existingCareers.size()) {
//                for (int i = existingCareers.size(); i < updatedCareers.size(); i++) {
//                    //!!!!!!!!!!//!!!!!!!!!!//!!!!!!!!!!//!!!!!!!!!!
//                }
//            }
//
//            log.info("resume : " + resume);
//            log.info("getCareers : " + resume.getCareers());
//
//            // 수정된 이력서 저장
//            resumeRepository.save(resume);
//            log.info("Resume with id {} updated successfully", id);
//        } else {
//            log.info("Resume with id {} not found", id);
//        }


    }

    @Transactional(rollbackFor = Exception.class)
    public List<Resume> getMyResumes(UserDto userDto) {
        Optional<User> userOptional =  userRepository.findById(userDto.getUserid());
        return resumeRepository.findAllByUser(userOptional.get());

    }



    @Transactional(rollbackFor = Exception.class)
    public void addResume(ResumeDto resumeDto) throws IOException {

        Resume resume = new Resume();

        resume.setEmail(resumeDto.getEmail());
        resume.setName(resumeDto.getName());
        resume.setPhone(resumeDto.getPhone());
        resume.setSchoolName(resumeDto.getSchoolName());
        resume.setMajor(resumeDto.getMajor());
        resume.setSummary(resumeDto.getSummary());
        resume.setCreationDate(LocalDateTime.now());
        resume.setGraduationYear(resumeDto.getGraduationYear());
        resume.setTitle(resumeDto.getTitle());
        resume.setSummary(resumeDto.getSummary());

        Optional<User> userOptional = userRepository.findById(resumeDto.getUserid());

        resume.setUser(userOptional.get());
        resumeRepository.save(resume);


        //경력
        for(CarrerDto carrerDto : resumeDto.getCarrer()){
            Carrer carrer = new Carrer();
            carrer.setCompanyName(carrerDto.getCompanyName());
            carrer.setPosition(carrerDto.getPosition());

            carrer.setStartDate(LocalDateTime.parse(carrerDto.getStartDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")));

            carrer.setEndDate(LocalDateTime.parse( carrerDto.getEndDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm") ));
            carrer.setResume(resume);
            careerRepository.save(carrer);
        }
        //자격증
        for(CertificationDto certificationDto : resumeDto.getCertification()){

            Certification certification = new Certification();
            certification.setCertificationName(certificationDto.getCertificationName());
            certification.setCertificationDate(LocalDateTime.parse( certificationDto.getCertificationDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")));
            certification.setResume(resume);
            certificationRepository.save(certification);
        }


        //저장 폴더 지정()
        String uploadPath= UPLOADPATH.ROOTDIRPATH+ File.separator+ UPLOADPATH.UPPERDIRPATH+ File.separator;
        uploadPath+=UPLOADPATH.RESUME + File.separator+ resumeDto.getUserid() + File.separator +resume.getId();

        File dir = new File(uploadPath);
        if(!dir.exists())
            dir.mkdirs();

        MultipartFile file = resumeDto.getFile();

        File fileobj = new File(dir,file.getOriginalFilename());

        file.transferTo(fileobj);

        // DB에 파일경로 저장
        String filePath= File.separator+ UPLOADPATH.UPPERDIRPATH+ File.separator;
        filePath+=UPLOADPATH.RESUME + File.separator+ resumeDto.getUserid() + File.separator +resume.getId()+File.separator+resumeDto.getFile().getOriginalFilename();
        resume.setFilePath(filePath);
        resumeRepository.save(resume);
    }
}




