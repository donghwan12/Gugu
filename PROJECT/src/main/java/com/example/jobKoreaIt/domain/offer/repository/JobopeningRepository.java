package com.example.jobKoreaIt.domain.offer.repository;

import com.example.jobKoreaIt.domain.offer.entity.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobopeningRepository extends JpaRepository<Recruit , Long> {
}
