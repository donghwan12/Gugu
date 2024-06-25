package com.example.jobKoreaIt.domain.seeker.entity;

import com.example.jobKoreaIt.domain.common.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobSeeker {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "userid",foreignKey = @ForeignKey(name="FK_JOB-SEEKER_USER2",
            foreignKeyDefinition ="FOREIGN KEY(userid) REFERENCES user(userid) ON DELETE CASCADE ON UPDATE CASCADE" ))
    private User user;
    private String username;
    private String email;
    private String tel;
    private String zipcode;
    private String addr1;
    private String addr2;
}
