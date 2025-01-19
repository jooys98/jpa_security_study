package com.example.jpa_study.repository;

import com.example.jpa_study.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductsRepository extends JpaRepository<Products, Long> {
    @Query("SELECT p FROM Products p LEFT JOIN FETCH p.mainCategory WHERE p.mainCategory.id = :mainCategoryId")
    public List<Products> getProductsByMainCategoryId(@Param("mainCategoryId") Long mainCategoryId);

    @Query("SELECT p FROM Products p LEFT JOIN FETCH p.subCategory WHERE p.subCategory.id = :subCategoryId")
    public List<Products> getProductsBySubCategoryId(@Param("subCategoryId") Long subCategoryId);

    @Query("SELECT p FROM Products p WHERE " +
            "(:searchTerm IS NULL OR :searchTerm = '' OR " +
            "p.productName LIKE CONCAT('%', :searchTerm, '%')) ")
    public List<Products> findBySearchTerm(String searchTerm);
} // 쿼리 수정 필요


