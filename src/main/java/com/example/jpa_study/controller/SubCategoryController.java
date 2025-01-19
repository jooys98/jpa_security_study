package com.example.jpa_study.controller;

import com.example.jpa_study.dto.ProductsDTO;
import com.example.jpa_study.dto.SubCategoryDTO;
import com.example.jpa_study.entity.SubCategory;
import com.example.jpa_study.repository.SubCategoryRepository;
import com.example.jpa_study.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/sub-category")
@RequiredArgsConstructor

public class SubCategoryController {

private final SubCategoryService subCategoryService;
    @GetMapping("/all")
    public ResponseEntity<List<SubCategoryDTO>> getAllSubCategory() {
    List<SubCategoryDTO> subCategoryDTOS =subCategoryService.getAllSubCategories();
    return ResponseEntity.ok(subCategoryDTOS);
    }

    @GetMapping("/products/{subCategoryId}")
    public ResponseEntity<List<ProductsDTO>> getAllProductsBySubCategoryId(@PathVariable Long subCategoryId) {
        List<ProductsDTO> productsDTOList = subCategoryService.readAllProductsBySubCategoryId(subCategoryId);
        return ResponseEntity.ok(productsDTOList);
    }//db 에 접근하는 로직은 전부 서비스에 때려박기
}
