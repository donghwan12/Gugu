package com.example.jobKoreaIt.domain.common.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Reply {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long rno;
    @ManyToOne
    @JoinColumn(name = "cno",foreignKey = @ForeignKey(name = "FK_reply_community",
            foreignKeyDefinition = "FOREIGN KEY (cno) REFERENCES community(no) ON DELETE CASCADE ON UPDATE CASCADE") ) //FK설정\
    private Community cno;
    private String username;
    private Long depth;     // 댓글깊이
    private Long ref;       // 참조
    private String content;
    private Long likecount;       //좋아요 Count
    private LocalDateTime regdate;  // 등록날자
    //private String profileimage;

}

