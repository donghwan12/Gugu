package com.example.jobKoreaIt.domain.seeker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumeDto {
    private Long id;
    private String email;
    private String name;
    private String phone;
    private LocalDate creationDate;

}
