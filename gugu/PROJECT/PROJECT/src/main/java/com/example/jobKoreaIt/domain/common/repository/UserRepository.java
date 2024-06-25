package com.example.jobKoreaIt.domain.common.repository;


import com.example.jobKoreaIt.domain.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

}
