package com.example.jobKoreaIt.domain.common.service;


import com.example.jobKoreaIt.domain.offer.entity.Recruit;
import com.example.jobKoreaIt.domain.offer.repository.RecruitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class RecruitServiceImpl {

    @Autowired
    RecruitRepository recruitRepository;


    @Transactional(rollbackFor = Exception.class)
    public List<Recruit> getAllRecruitDesc() {

        return recruitRepository.findAllByOrderByIdDesc();
    }

    @Transactional(rollbackFor = Exception.class)

    public Recruit getRecruit(Long id) {
        return recruitRepository.findById(id).get();
    }
}
