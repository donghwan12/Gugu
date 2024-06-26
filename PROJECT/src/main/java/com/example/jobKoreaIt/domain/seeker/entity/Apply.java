package com.example.jobKoreaIt.domain.seeker.entity;

import com.example.jobKoreaIt.domain.offer.entity.Recruit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Apply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long apply_id;

    private String Nationality; //국적
    private String ExpectedSalary; //희망연봉
    private String MilitaryService; //병역유무
    private String Objective; //지원종목

    @ManyToOne
    @JoinColumn(name = "resume_id", foreignKey = @ForeignKey(name = "FK_Apply_Resume",
            foreignKeyDefinition = "FOREIGN KEY(resume_id) REFERENCES Resume(id) ON DELETE CASCADE ON UPDATE CASCADE"))
    private Resume resume;

    @ManyToOne
    @JoinColumn(name = "job_seeker_id", foreignKey = @ForeignKey(name="FK_JobSeeker_Apply",
            foreignKeyDefinition ="FOREIGN KEY(job_seeker_id) REFERENCES job_seeker(id) ON DELETE CASCADE ON UPDATE CASCADE" ))
    private JobSeeker jobSeeker;

    @ManyToOne
    @JoinColumn(name = "recruit_id", foreignKey = @ForeignKey(name="FK_Recruit_Apply",
            foreignKeyDefinition ="FOREIGN KEY(recruit_id) REFERENCES Recruit(id) ON DELETE CASCADE ON UPDATE CASCADE" ))
    private Recruit recruit;

    // getter, setter, toString 등 필요한 메서드 추가
}
