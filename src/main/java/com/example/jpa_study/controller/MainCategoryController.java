package com.example.jpa_study.controller;

import com.example.jpa_study.dto.ProductsDTO;
import com.example.jpa_study.dto.SubCategoryDTO;
import com.example.jpa_study.entity.MainCategory;
import com.example.jpa_study.entity.Products;
import com.example.jpa_study.repository.MainCategoryRepository;
import com.example.jpa_study.repository.ProductsRepository;
import com.example.jpa_study.repository.SubCategoryRepository;
import com.example.jpa_study.service.MainCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/main-category")
@RequiredArgsConstructor

public class MainCategoryController {

private final MainCategoryService mainCategoryService;
    private final ProductsRepository productsRepository;
    private final MainCategoryRepository mainCategoryRepository;
    private final SubCategoryRepository subCategoryRepository;

    @GetMapping("/products/{mainCategoryId}") // 메인카테고리 별 상품 보여주기 /
    public ResponseEntity<List<ProductsDTO>> getAllProducts(@PathVariable Long mainCategoryId) {
   MainCategory category = mainCategoryRepository.findById(mainCategoryId).orElseThrow(()->new RuntimeException("메인 카태고리 아이디 조회 실패 "));
 List<ProductsDTO> productsDTOS = mainCategoryService.getProductsByMainCategoryId(category.getId());
 return ResponseEntity.ok(productsDTOS);

}

}
