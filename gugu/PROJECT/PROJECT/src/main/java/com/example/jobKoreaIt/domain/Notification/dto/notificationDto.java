package com.example.jobKoreaIt.domain.Notification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class notificationDto {
    private Long id;
    private String title;
    private String author;
    private String contents; //내용
    private LocalDateTime createAt; //공지사항 생성 된 날짜
    private LocalDateTime updateAt;
}
