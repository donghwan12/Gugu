package com.example.jobKoreaIt.domain.common.repository;


import com.example.jobKoreaIt.domain.common.entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityRepository extends JpaRepository<Community,Long> {


    @Query(value = "SELECT * FROM KoreaJob.community ORDER BY no DESC LIMIT :amount OFFSET :offset", nativeQuery = true)
    List<Community> findCommunityAmountStart(@Param("amount") int amount, @Param("offset") int offset);

    //category선별  -  Type , keyword 로 필터링된 count 계산
    @Query("SELECT COUNT(b) FROM Community b WHERE  b.category = :category and b.title LIKE %:keyword%" )
    Integer countByCategoryAndTitle(@Param("category")String category,@Param("keyword")String keyword);

    //category선별 -  Type, username로 필터링된 count 계산
    @Query("SELECT COUNT(b) FROM Community b WHERE  b.category = :category and b.username LIKE %:keyword%" )
    Integer countByCategoryAndUsername(@Param("category")String category,@Param("keyword")String keyword);

    //category선별 -  Type, content로 필터링된 count 계산
    @Query("SELECT COUNT(b) FROM Community b WHERE  b.category = :category and b.content LIKE %:keyword%" )
    Integer countByCategoryAndContent(@Param("category")String category,@Param("keyword")String keyword);


    @Query("SELECT COUNT(b) FROM Community b WHERE b.category LIKE %:category%")
    Integer countByCategory(@Param("category")String category);

    @Query(value = "SELECT * FROM Community b WHERE b.category = :category and b.title LIKE %:keyword%  ORDER BY b.no DESC LIMIT :amount OFFSET :offset", nativeQuery = true)
    List<Community> findAllByCatAndTitle(@Param("category")String category,@Param("keyword")String keyword, @Param("amount") int amount,@Param("offset") int offset);

    @Query(value = "SELECT * FROM Community b WHERE b.category = :category and b.username LIKE %:keyword%  ORDER BY b.no DESC LIMIT :amount OFFSET :offset", nativeQuery = true)
    List<Community> findAllByCatAndUsername(@Param("category")String category,@Param("keyword")String keyword, @Param("amount") int amount,@Param("offset") int offset);

    @Query(value = "SELECT * FROM Community b WHERE b.category = :category and b.content LIKE %:keyword%  ORDER BY b.no DESC LIMIT :amount OFFSET :offset", nativeQuery = true)
    List<Community> findAllByCatAndContent(@Param("category")String category,@Param("keyword")String keyword, @Param("amount") int amount,@Param("offset") int offset);

    @Query(value = "SELECT * FROM Community b WHERE b.category = :category  ORDER BY b.no DESC LIMIT :amount OFFSET :offset", nativeQuery = true)
    List<Community> findAllByOnlyCategory(@Param("category")String category, @Param("amount") int amount,@Param("offset") int offset);

    @Query(value = "SELECT * FROM Community ORDER BY no DESC LIMIT :amount OFFSET :offset", nativeQuery = true)
    List<Community> findAllByAmountStart(@Param("amount") int amount,@Param("offset") int offset);
}
