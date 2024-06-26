package com.example.jobKoreaIt.domain.common.repository;

import com.example.jobKoreaIt.domain.common.entity.Community;
import com.example.jobKoreaIt.domain.common.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<Reply,Long> {

    @Query("SELECT r FROM Reply r WHERE cno = :cno ORDER BY rno DESC")
    List<Reply> GetReplyByCommunityNoDesc(@Param("cno") Community community);

    @Query("SELECT COUNT(r) FROM Reply r WHERE cno = :cno")
    Long GetReplyCountByCommunityNoDesc(@Param("cno") Community community);


}
