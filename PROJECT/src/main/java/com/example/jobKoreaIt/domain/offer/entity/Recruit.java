package com.example.jobKoreaIt.domain.offer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="recruit")
public class Recruit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "job_offer_id",foreignKey = @ForeignKey(name="FK_OFFER_RECRUIT",
    foreignKeyDefinition ="FOREIGN KEY(job_offer_id) REFERENCES job_offer(id) ON DELETE CASCADE ON UPDATE CASCADE" ))
    JobOffer jobOffer;

    private String title;           // 제목
    private String career;          // 경력
    private String department;
    private String ability;         // 학력
    private String jobwork;         // 근무형태
    private String jobzone;         // 근무지역
    private String recuitStatus;
    private String detailAddress;
    private String salary;
    private String jobspecial;
    private String welfare;         // 복리후생(복지)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startTime;        // 접수기간
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime endTime;        // 접수기간
    private String jobway;          // 접수방법
    private String jobpapers;       // 제출서류
    private String filePath;           // 이미지
//    private MultipartFile[] files;  // 이미지

}
