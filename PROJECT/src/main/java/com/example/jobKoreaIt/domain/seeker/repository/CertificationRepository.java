package com.example.jobKoreaIt.domain.seeker.repository;


import com.example.jobKoreaIt.domain.seeker.entity.Certification;
import com.example.jobKoreaIt.domain.seeker.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificationRepository extends JpaRepository<Certification,Long> {
    List<Certification> findAllByResume(Resume resume);
}
