package com.example.jobKoreaIt.domain.seeker.dto;

import com.example.jobKoreaIt.domain.seeker.entity.Resume;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplyDto {


    private long id;

    private String name;
    private String tell;
    private String email;
    private String addr; //주소
    private String nationality; //국적
    private String birthDate; //생년월일
    private String expectedSalary; //희망연봉
    private String militaryService; //병역사항

    private Resume resume;

    private String Objective; //지원종목
}
