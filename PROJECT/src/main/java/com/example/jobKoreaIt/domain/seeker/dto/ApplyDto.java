package com.example.jobKoreaIt.domain.seeker.dto;

import com.example.jobKoreaIt.domain.seeker.entity.JobSeeker;
import com.example.jobKoreaIt.domain.seeker.entity.Resume;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplyDto {


    private long id;

    private Long resume_id;
    private Long recruit_id;

    private String seeker_status;
    private String offer_status;

}
