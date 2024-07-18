package com.example.jobKoreaIt.domain.seeker.entity;

import com.example.jobKoreaIt.domain.common.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Resume {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String name;
    private String phone;
    private String title;

    @ManyToOne
    @JoinColumn(name = "userid",foreignKey = @ForeignKey(name="FK_JOB-SEEKER_RESUME_2",
            foreignKeyDefinition ="FOREIGN KEY(userid) REFERENCES user(userid) ON DELETE CASCADE ON UPDATE CASCADE" ))
    private User user;


    // 학력
    private String schoolName;
    private String major;
    private String graduationYear;

    // 자기소개서
    @Column(length = 1000)
    private String summary;

    // 작성 날짜
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime creationDate;

    private String filePath;
}
