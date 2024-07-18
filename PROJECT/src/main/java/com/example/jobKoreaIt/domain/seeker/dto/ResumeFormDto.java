package com.example.jobKoreaIt.domain.seeker.dto;

import com.example.jobKoreaIt.domain.seeker.entity.Carrer;
import com.example.jobKoreaIt.domain.seeker.entity.Resume;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumeFormDto {
    private Resume resume;
    private List<Carrer> careers;
}
