package com.example.jpa_study.service;

import com.example.jpa_study.dto.ProductsDTO;
import com.example.jpa_study.entity.Products;
import com.example.jpa_study.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductsServiceImpl implements ProductsService {


    private final ProductsRepository productsRepository;


    @Override
    public List<ProductsDTO> readAllProducts() {
        List<ProductsDTO> productsDTOS = productsRepository.findAll()
                .stream().map(this::convertToProductsDTO).collect(Collectors.toList());
        return productsDTOS;
    }

    @Override //검색
    public List<ProductsDTO> searchByProductName(String searchTerm) {
        List<ProductsDTO> searchProducts = productsRepository.findBySearchTerm(searchTerm)
                .stream().map(this::convertToProductsDTO).collect(Collectors.toList());
        return searchProducts;
    }

    @Override
    public ProductsDTO getProductDetail(Long id) {
        Products products = productsRepository.findById(id).orElse(null);
        return convertToProductsDTO(products);
    }

}
