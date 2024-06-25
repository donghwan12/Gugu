package com.example.jobKoreaIt.domain.common.service;


import com.example.jobKoreaIt.domain.common.dto.CommunityDto;
import com.example.jobKoreaIt.domain.common.dto.Criteria;
import com.example.jobKoreaIt.domain.common.dto.PageDto;
import com.example.jobKoreaIt.domain.common.entity.Community;
import com.example.jobKoreaIt.domain.common.repository.CommunityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class CommunityServiceImpl {

    @Autowired
    private CommunityRepository communityRepository;

    private String uploadDir = "c:\\upload";


    @Transactional(rollbackFor = Exception.class)
    public boolean addCommunity(CommunityDto dto) throws IOException {

        Community community = new Community();
        community.setTitle(dto.getTitle());
        community.setContent(dto.getContent());
        community.setUsername(dto.getUsername());
        community.setCount(0L);
        community.setRegdate(LocalDateTime.now());
        community.setCategory(dto.getCategory());


        community  =    communityRepository.save(community);

        boolean issaved =  communityRepository.existsById(community.getNo());

        return issaved;
    }



    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> GetCommunityList(Criteria criteria) {
        Map<String,Object> returns = new HashMap<String,Object>();


        //--------------------------------------------------------
        //SEARCH
        //--------------------------------------------------------
        int totalcount=0;
        if(criteria!=null&& criteria.getCategory()!=null) {
            if (criteria.getType().equals("title"))
                totalcount = communityRepository.countByCategoryAndTitle(criteria.getCategory(),criteria.getKeyword());
            else if (criteria.getType().equals("username"))
                totalcount = communityRepository.countByCategoryAndUsername(criteria.getCategory(),criteria.getKeyword());
            else if (criteria.getType().equals("content"))
                totalcount = communityRepository.countByCategoryAndContent(criteria.getCategory(),criteria.getKeyword());
            else
               totalcount = communityRepository.countByCategory(criteria.getCategory());    //카테고리별 전체검색
        }
        else
            totalcount = (int)communityRepository.count();  //모두 검색

        System.out.println("COUNT  :" + totalcount);

        //PageDto 만들기
        PageDto pagedto = new PageDto(totalcount,criteria);

        //시작 게시물 번호 구하기(수정) - OFFSET
        int offset =(criteria.getPageno()-1) * criteria.getAmount();    //1page = 0, 2page = 10


        //------------------statu-------------------------------------
        //SEARCH
        //--------------------------------------------------------
        List<Community> list = null;
        if(criteria!=null&& criteria.getCategory()!=null) {
            if (criteria.getType().equals("title")) {
                list = communityRepository.findAllByCatAndTitle(criteria.getCategory(), criteria.getKeyword(), pagedto.getCriteria().getAmount(), offset);
                System.out.println("TITLE SEARCH!");
                System.out.println(list);
            } else if (criteria.getType().equals("username"))
                list = communityRepository.findAllByCatAndUsername(criteria.getCategory(),criteria.getKeyword(), pagedto.getCriteria().getAmount(), offset);
            else if (criteria.getType().equals("content"))
                list = communityRepository.findAllByCatAndContent(criteria.getCategory(),criteria.getKeyword(), pagedto.getCriteria().getAmount(), offset);
            else
                list = communityRepository.findAllByOnlyCategory(criteria.getCategory(),pagedto.getCriteria().getAmount(), offset);
        }
        else
            list  =  communityRepository.findAllByAmountStart(pagedto.getCriteria().getAmount(),offset);

        returns.put("list",list);
        returns.put("pageDto",pagedto);
        returns.put("total",totalcount);


        return returns;
    }
    @Transactional(rollbackFor = Exception.class)
    public Community getCommunity(Long no) {
        return communityRepository.findById(no).get();

    }
    @Transactional(rollbackFor = Exception.class)
    public boolean updateCommunity(CommunityDto dto) {
        Community community = new Community();
        community.setNo(dto.getNo());
        community.setTitle(dto.getTitle());
        community.setContent(dto.getContent());
        community.setUsername(dto.getUsername());
        community.setCount(dto.getCount());
        community.setRegdate(dto.getRegdate());
        community.setCategory(dto.getCategory());
        communityRepository.save(community);
        return true;
    }
    @Transactional(rollbackFor = Exception.class)
    public void removeCommunity(Long no) {
        communityRepository.deleteById(no);
    }

    @Transactional(rollbackFor = Exception.class)
    public void count(Long no) {
        Community community =  communityRepository.findById(no).get();
        community.setCount(community.getCount()+1);
        communityRepository.save(community);
    }






}
