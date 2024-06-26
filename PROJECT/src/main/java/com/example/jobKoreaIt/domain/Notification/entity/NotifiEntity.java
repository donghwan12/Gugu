package com.example.jobKoreaIt.domain.Notification.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NotifiEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;

    @Column(name = "contents", columnDefinition = "LONGTEXT")
    private String contents;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;


}
