package com.example.jobKoreaIt.domain.offer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecruitDto {
    private Long id;
    private String title;           // 제목
    private String career;      // 경력
    private String department;
    private String ability;         // 학력
    private String jobwork;         // 근무형태
    private String recuitStatus;
    private String money;           // 급여
    private String jobzone;         // 근무지역
    private String detailAddress;
    private String salary;
    private String jobspecial;      // 우대사항
    private String welfare;         // 복리후생(복지)
    private LocalDateTime startTime;        // 접수기간
    private LocalDateTime endTime;        // 접수기간
    private String jobway;          // 접수방법
    private String jobpapers;           // 제출서류
    private MultipartFile file;       // 이미지
}
