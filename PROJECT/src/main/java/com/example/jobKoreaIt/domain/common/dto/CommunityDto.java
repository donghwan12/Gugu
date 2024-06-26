package com.example.jobKoreaIt.domain.common.dto;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommunityDto {
    private Long no;
    @NotBlank(message = "username을 입력하세요")
    @Email(message = "올바른 이메일 주소를 입력하세요")
    private String username;
    private String title;
    private String content;
    private String category;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regdate;
    private Long count;

    private MultipartFile[] files;
}
