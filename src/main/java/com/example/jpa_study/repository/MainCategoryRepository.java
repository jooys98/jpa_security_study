package com.example.jpa_study.repository;

import com.example.jpa_study.entity.MainCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MainCategoryRepository extends JpaRepository<MainCategory, Long> {


    @Query("SELECT m FROM MainCategory m LEFT JOIN FETCH m.subCategories WHERE m.id = :mainCategoryId")
    public List<MainCategory> findBySubCategories(@Param("mainCategoryId") Long mainCategoryId);
//메인 카테고리 아이디로 해당 서브 카테고리를 찾는다


}
