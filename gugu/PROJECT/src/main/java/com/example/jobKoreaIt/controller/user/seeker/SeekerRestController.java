package com.example.jobKoreaIt.controller.user.seeker;

import com.example.jobKoreaIt.domain.seeker.service.JobSeekerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/seeker")
public class SeekerRestController {

    //------------------
    //이력서 CRUD (이동환)
    //------------------

    @Autowired
    private JobSeekerServiceImpl jobSeekerServiceImpl;
}
