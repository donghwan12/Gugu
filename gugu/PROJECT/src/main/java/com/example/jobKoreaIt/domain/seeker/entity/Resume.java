package com.example.jobKoreaIt.domain.seeker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 기본 정보
    private String name;
    private String email;
    private String phone;

    // 학력
    private Long educationId;
    private String schoolName;
    private String major;
    private String graduationYear;

    // 경력
    private String companyName;
    private String position;
    private String startDate;
    private String endDate;


    // 기술 및 자격증
    private String certificationName;

    // 자기소개서
    @Column(length = 1000)
    private String summary;

    // 기타
    private String hobbies;

}
