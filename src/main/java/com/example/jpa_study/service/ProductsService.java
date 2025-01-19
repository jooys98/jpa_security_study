package com.example.jpa_study.service;

import com.example.jpa_study.dto.ProductsDTO;
import com.example.jpa_study.entity.Products;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductsService {


    List<ProductsDTO> readAllProducts();

    List<ProductsDTO> searchByProductName(String searchTerm);

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

    ProductsDTO getProductDetail(Long id);
}
