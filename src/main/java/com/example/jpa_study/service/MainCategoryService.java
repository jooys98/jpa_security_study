package com.example.jpa_study.service;

import com.example.jpa_study.dto.ProductsDTO;
import com.example.jpa_study.dto.SubCategoryDTO;
import com.example.jpa_study.entity.Products;
import com.example.jpa_study.entity.SubCategory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface MainCategoryService {
 public List <ProductsDTO> getProductsByMainCategoryId(Long mainCategoryId);



    //product 엔티티  -> products dto
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
