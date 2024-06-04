package com.example.jobKoreaIt.domain.common.entity;


import com.example.jobKoreaIt.domain.common.dto.UserDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {
    @Id
    private String username;
    private String password;
    private String role;

    private LocalDateTime createAt;

    //OAUTH2
    private String provider;
    private String providerId;


}
