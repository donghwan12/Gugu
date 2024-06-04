package com.example.jobKoreaIt.domain.seeker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="seeker")
public class JobSeeker {
    @Id
    private Long id;

    private String tel;
    private String zipcode;
    private String addr1;
    private String addr2;

    private String ability;

}
