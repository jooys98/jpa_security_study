package com.example.jpa_study.controller;

import com.example.jpa_study.dto.ProductsDTO;
import com.example.jpa_study.entity.Products;
import com.example.jpa_study.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/product")
@RequiredArgsConstructor
@RestController
public class ProductsController {

    private final ProductsService productsService;

    @GetMapping("/all") //모든 상품
    public ResponseEntity<List<ProductsDTO>> getAllProducts() {
List<ProductsDTO> productsDTOS = productsService.readAllProducts();
return ResponseEntity.ok(productsDTOS);
    }


    @GetMapping("/search") //상품 검색결과 리스트
    public ResponseEntity<List<ProductsDTO>> getSearchByProduct(@RequestParam String searchTerm) {
        List<ProductsDTO> searchProductList = productsService.searchByProductName(searchTerm);
        return ResponseEntity.ok(searchProductList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductsDTO> getProductById(@PathVariable Long id) {
        ProductsDTO productsDetail = productsService.getProductDetail(id);
        return ResponseEntity.ok(productsDetail);
    }
}
