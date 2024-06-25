package com.example.jobKoreaIt.domain.offer.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobOfferDto {
    private Long id;
    private String companyName;     // 이름
    private String companyNumber; //// 사업자 번호
    private String zipcode;
    private String companyAddr1;     // 주소
    private String companyAddr2;     // 주소

    private String companyEmail;    // 이메일
    private String companyPhone;    // 폰
    private String companyIndustry; // 업종
    private String companyexplanation;  // 회사 설명

}
