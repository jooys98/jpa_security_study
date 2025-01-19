package com.example.jpa_study.service;

import com.example.jpa_study.dto.ProductsDTO;
import com.example.jpa_study.dto.SubCategoryDTO;
import com.example.jpa_study.entity.Products;
import com.example.jpa_study.entity.SubCategory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface SubCategoryService {
 public List<SubCategoryDTO> getAllSubCategories();

    List<ProductsDTO> readAllProductsBySubCategoryId(Long mainCategoryId);


public default  SubCategoryDTO toDto(SubCategory subCategory){
    return SubCategoryDTO.builder()
            .id(subCategory.getId())
            .subCategoryName(subCategory.getSubCategoryName())
            .build();
}

    public default ProductsDTO convertToProductsDTO(Products products){
        return ProductsDTO.builder()
                .id(products.getId())
                .productName(products.getProductName())
                .productDescription(products.getProductDescription())
                .productPrice(products.getProductPrice())
                .mainCategoryName(products.getMainCategoryName())
                .subCategoryName(products.getSubCategoryName())
                .build();
    }

}
