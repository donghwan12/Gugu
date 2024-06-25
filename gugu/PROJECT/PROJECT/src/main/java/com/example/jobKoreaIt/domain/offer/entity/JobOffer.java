package com.example.jobKoreaIt.domain.offer.entity;


import com.example.jobKoreaIt.domain.common.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobOffer{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userid", foreignKey = @ForeignKey(name="FK_JOB-OFFER_USER2",
            foreignKeyDefinition ="FOREIGN KEY(userid) REFERENCES user(userid) ON DELETE CASCADE ON UPDATE CASCADE" ))
    User user;

    //회사정보
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
