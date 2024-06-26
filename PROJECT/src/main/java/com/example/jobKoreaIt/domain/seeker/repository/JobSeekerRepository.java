package com.example.jobKoreaIt.domain.seeker.repository;


import com.example.jobKoreaIt.domain.common.entity.User;
import com.example.jobKoreaIt.domain.seeker.entity.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface JobSeekerRepository extends JpaRepository<JobSeeker,String> {

    JobSeeker findByUser(User user);

    JobSeeker findByUsernameAndTel(String username, String Tel);

    JobSeeker findByUsername(String applyDto);

    JobSeeker findByTel(String applyDto);
}
