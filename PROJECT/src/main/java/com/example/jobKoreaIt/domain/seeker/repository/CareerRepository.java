package com.example.jobKoreaIt.domain.seeker.repository;

import com.example.jobKoreaIt.domain.seeker.entity.Carrer;
import com.example.jobKoreaIt.domain.seeker.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CareerRepository extends JpaRepository<Carrer,Long> {
    void deleteAllByResume(Resume resume);

    List<Carrer> findAllByResume(Resume resume);
}
