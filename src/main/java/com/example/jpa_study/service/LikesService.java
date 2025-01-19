package com.example.jpa_study.service;

import com.example.jpa_study.dto.ProductsDTO;
import com.example.jpa_study.entity.Products;

import java.util.List;

public interface LikesService {
    List<ProductsDTO> getLikedListByUserId(Long userId);


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

    boolean changeLikes(Long userId, Long productId);
}
