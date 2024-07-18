package com.example.jobKoreaIt.domain.seeker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Carrer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  String companyName;
    private String position;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @ManyToOne
    @JoinColumn(name = "resume_id",foreignKey = @ForeignKey(name="FK_Resume_Carrer_0",
            foreignKeyDefinition ="FOREIGN KEY(resume_id) REFERENCES resume(id) ON DELETE CASCADE ON UPDATE CASCADE" ))
    private Resume resume;


}
