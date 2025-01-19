package com.example.jpa_study.service;

import com.example.jpa_study.dto.ProductsDTO;
import com.example.jpa_study.dto.SubCategoryDTO;
import com.example.jpa_study.entity.Products;
import com.example.jpa_study.entity.SubCategory;
import com.example.jpa_study.repository.ProductsRepository;
import com.example.jpa_study.repository.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;
    private final ProductsRepository productsRepository;

    @Override
    public List<SubCategoryDTO> getAllSubCategories() {
        List<SubCategory> subCategoryList = subCategoryRepository.findAll();
        List<SubCategoryDTO> subCategoryDTOS = subCategoryList.stream()
                .map(this::toDto).collect(Collectors.toList());
        return subCategoryDTOS;
    }

    @Override //서브 카테고리 아이디별로 프로덕트 보여주기
    public List<ProductsDTO> readAllProductsBySubCategoryId(Long subCategoryId) {
        SubCategory subCategory = subCategoryRepository.findById(subCategoryId).orElseThrow(()->new RuntimeException("빠가야로 ㅋ"));
        Long selectedSubCategoryId = subCategory.getId();
        List<Products> productsList = productsRepository.getProductsBySubCategoryId(selectedSubCategoryId);
        List<ProductsDTO> productsDTOS = productsList.stream().map(this::convertToProductsDTO).collect(Collectors.toList());
        return productsDTOS;
    }
}
