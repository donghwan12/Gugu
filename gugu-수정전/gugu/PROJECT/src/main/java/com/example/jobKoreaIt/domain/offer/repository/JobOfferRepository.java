package com.example.jobKoreaIt.domain.offer.repository;


import com.example.jobKoreaIt.domain.offer.entity.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobOfferRepository extends JpaRepository<JobOffer,Long> {
}
