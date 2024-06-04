package com.example.jobKoreaIt.domain.offer.service;


import com.example.jobKoreaIt.domain.offer.repository.JobOfferRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class JobOfferServiceImpl {

    @Autowired
    private JobOfferRepository offerRepository;

    @Transactional(rollbackFor = Exception.class)
    public void function(){
        log.info("TEST...");
    }
}
