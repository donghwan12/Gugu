package com.example.jobKoreaIt.domain.common.repository;

import com.example.jobKoreaIt.domain.common.dto.CommunityDto;
import com.example.jobKoreaIt.domain.common.entity.Community;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class CommunityRepositoryTest {

    @Autowired
    private CommunityRepository communityRepository;


    // POST 1000
    @Test
    public void t1(){
        String cat [] = {"신입","취준","채용공고","자소서","면접","QnA","커리어","이직","퇴사","잡담"};
        for(int i=1;i<=1000;i++){
            Community community = new Community();
            community.setNo(0L+i);
            community.setTitle("제목" + i);
            community.setContent("내용" + i);
            community.setCount(0L);
            community.setCategory("");
            community.setUsername("user" + i + "@naver.com");
            community.setRegdate(LocalDateTime.now());
            communityRepository.save(community);
        }
    }

}