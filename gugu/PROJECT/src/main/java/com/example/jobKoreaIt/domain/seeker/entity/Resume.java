package com.example.jobKoreaIt.domain.seeker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private String schoolName;
    private String major;
    private String graduationYear;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Career> careers = new ArrayList<>();

    // 기술 및 자격증
    private String certificationName;

    // 자기소개서
    @Column(length = 1000)
    private String summary;

    // 기타
    private String hobbies;

    // 작성 날짜
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime creationDate;

    // 작성 날짜 설정 메서드
    @PrePersist
    protected void onCreate() {
        creationDate = LocalDateTime.now();
    }
    @Override
    public String toString() {
        return "Resume{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", major='" + major + '\'' +
                ", graduationYear='" + graduationYear + '\'' +
                ", certificationName='" + certificationName + '\'' +
                ", summary='" + summary + '\'' +
                ", hobbies='" + hobbies + '\'' +
                // Avoid printing careers to prevent StackOverflowError
                '}';
    }

    public void addCareer(Career career) {
        careers.add(career);
        career.setResume(this);
    }

    public void removeCareer(Career career) {
        careers.remove(career);
        career.setResume(null);
    }
}
