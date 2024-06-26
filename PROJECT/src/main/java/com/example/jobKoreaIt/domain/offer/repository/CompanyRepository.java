package com.example.jobKoreaIt.domain.offer.repository;


import com.example.jobKoreaIt.domain.offer.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {


}
