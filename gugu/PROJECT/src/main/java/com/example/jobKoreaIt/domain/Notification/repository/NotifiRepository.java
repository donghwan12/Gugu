package com.example.jobKoreaIt.domain.Notification.repository;

import com.example.jobKoreaIt.domain.Notification.entity.NotifiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotifiRepository extends JpaRepository<NotifiEntity,Long> {

    @Query(value = "SELECT * FROM koreajob.notifi_entity ORDER BY id DESC LIMIT :amount OFFSET :offset", nativeQuery = true)
    List<NotifiEntity> findCommunityAmountStart(@Param("amount") int amount, @Param("offset") int offset);
}
