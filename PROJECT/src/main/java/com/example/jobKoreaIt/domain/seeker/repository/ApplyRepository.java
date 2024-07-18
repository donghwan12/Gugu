package com.example.jobKoreaIt.domain.seeker.repository;

import com.example.jobKoreaIt.domain.offer.entity.Recruit;
import com.example.jobKoreaIt.domain.seeker.entity.Apply;
import com.example.jobKoreaIt.domain.seeker.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplyRepository extends JpaRepository<Apply,Long> {
    List<Apply> findAllByRecruit(Recruit recruit);

    Apply findByResume(Resume resume);

    Apply findByResumeAndRecruit(Resume resume, Recruit recruit);
}
