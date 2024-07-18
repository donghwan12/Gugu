package com.example.jobKoreaIt.domain.offer.repository;

import com.example.jobKoreaIt.domain.offer.entity.JobOffer;
import com.example.jobKoreaIt.domain.offer.entity.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobopeningRepository extends JpaRepository<Recruit , Long> {
    List<Recruit> findAllByJobOfferOrderByIdDesc(JobOffer jobOffer);
}
