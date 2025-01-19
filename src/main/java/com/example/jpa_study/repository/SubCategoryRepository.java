package com.example.jpa_study.repository;

import com.example.jpa_study.dto.SubCategoryDTO;
import com.example.jpa_study.entity.Products;
import com.example.jpa_study.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

}
