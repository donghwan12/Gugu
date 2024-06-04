package com.example.jobKoreaIt.domain.seeker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Career {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;
    private String position;
    private String startDate;
    private String endDate;

    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;
}
