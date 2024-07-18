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
public class Certification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String certificationName;
    private LocalDateTime certificationDate;


    @ManyToOne
    @JoinColumn(name = "resume_id",foreignKey = @ForeignKey(name="FK_Resume_Certification",
            foreignKeyDefinition ="FOREIGN KEY(resume_id) REFERENCES resume(id) ON DELETE CASCADE ON UPDATE CASCADE" ))
    private Resume resume;

}
