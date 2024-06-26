package com.example.jobKoreaIt.domain.Notification.repository;

import com.example.jobKoreaIt.domain.Notification.entity.NotifiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotifiRepository extends JpaRepository<NotifiEntity,Long> {
}
