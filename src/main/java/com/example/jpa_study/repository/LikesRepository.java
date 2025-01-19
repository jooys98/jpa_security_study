package com.example.jpa_study.repository;

import com.example.jpa_study.entity.Likes;
import com.example.jpa_study.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    //@Query("select l.product from Likes l where l.user.id = :user_id")
    // public List<Products> findLikedProductsByUserId(@Param("user_id") Long userId);
    //해당 프로덕트 전체 정보
    @Query("SELECT l.product FROM Likes l WHERE l.user.id = :user_id")
    public List<Products> findLikedProductsByUserId(@Param("user_id") Long userId);
   //프로덕트 아이디들 리스트로 ..

    boolean existsByUserIdAndProductId(Long userId, Long productId);


    @Modifying  // DELETE 쿼리이므로 @Modifying 필요
    @Transactional // 데이터 변경이므로 트랜잭션 필요
    @Query("DELETE FROM Likes l WHERE l.user.id = :userId AND l.product.id = :productId")
    void deleteUserProducts(@Param("userId") Long userId, @Param("productId") Long productId);
}
