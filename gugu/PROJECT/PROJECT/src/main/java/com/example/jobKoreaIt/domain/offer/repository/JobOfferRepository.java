package com.example.jobKoreaIt.domain.offer.repository;


import com.example.jobKoreaIt.domain.common.entity.User;
import com.example.jobKoreaIt.domain.offer.entity.JobOffer;
import com.example.jobKoreaIt.domain.seeker.entity.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobOfferRepository extends JpaRepository<JobOffer,Long> {
    JobOffer findByUser(User user);

    JobOffer findByCompanyNumberAndCompanyEmail(String companyNumber, String companyEmail);
}
