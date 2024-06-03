package com.example.jobKoreaIt.domain.offer.entity;


import com.example.jobKoreaIt.domain.common.entity.User;
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
@Table(name="offer")
public class JobOffer{
    @Id
    private Long id;
    private String offerName;
    private String offerTuserel;
    private String offerAddress;

}
