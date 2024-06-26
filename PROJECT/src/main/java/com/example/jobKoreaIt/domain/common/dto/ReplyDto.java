package com.example.jobKoreaIt.domain.common.dto;


import com.example.jobKoreaIt.domain.common.entity.Community;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReplyDto {
    private Long rno;
    private Long community_no;
    private String username;
    private Long depth;     // 댓글깊이
    private Long ref;       // 참조
    private String content;
    private Long likecount;       //좋아요 Count
    private LocalDateTime regdate;  // 등록날자
    //private String profileimage;

}
