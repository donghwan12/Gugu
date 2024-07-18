package com.example.jobKoreaIt.domain.seeker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumeDto {
    private Long id;
    private String title;
    private String email;
    private String name;
    private String phone;
    private String userid;
    private String creationDate;

    private CarrerDto[] carrer;
    private CertificationDto[] certification;
    private MultipartFile file;


    //--------------
    //새로 추가됨
    //--------------
    private String schoolName;
    private String major;

    private String graduationYear;

    private String summary;




}
