package com.example.jpa_study.service;

import com.example.jpa_study.dto.ProductsDTO;
import com.example.jpa_study.dto.SubCategoryDTO;
import com.example.jpa_study.entity.MainCategory;
import com.example.jpa_study.entity.Products;
import com.example.jpa_study.entity.SubCategory;
import com.example.jpa_study.repository.MainCategoryRepository;
import com.example.jpa_study.repository.ProductsRepository;
import com.example.jpa_study.repository.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor

public class MainCategoryServiceImpl implements MainCategoryService {

    private final MainCategoryRepository mainCategoryRepository;
    private final ProductsRepository productsRepository;
    private final SubCategoryRepository subCategoryRepository;

    @Override
    public List<ProductsDTO> getProductsByMainCategoryId(Long mainCategoryId) {
        List<Products> products = productsRepository.getProductsByMainCategoryId(mainCategoryId);
        return products.stream().map(this::convertToProductsDTO)
                .collect(Collectors.toList()); //productdto -> list 로 수집
    }



}
