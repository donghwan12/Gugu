package com.example.jobKoreaIt.domain.seeker.dto;

import com.example.jobKoreaIt.domain.common.dto.UserDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class JobSeekerDto  {
    private Long id;
    private String tel;
    private String username;
    private String zipcode;
    private String addr1;
    private String addr2;
    private String email;
}
