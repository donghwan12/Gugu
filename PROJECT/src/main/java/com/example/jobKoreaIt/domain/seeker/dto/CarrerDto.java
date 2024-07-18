package com.example.jobKoreaIt.domain.seeker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarrerDto {

    private String companyName;
    private String position;

    private String startDate;
    private String endDate;


}
