package com.example.jobKoreaIt.domain.seeker.repository;

import com.example.jobKoreaIt.domain.seeker.entity.Career;
import com.example.jobKoreaIt.domain.seeker.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CareerRepository extends JpaRepository<Career,Long> {
    void deleteAllByResume(Resume resume);

    List<Career> findAllByResume(Resume resume);
}
