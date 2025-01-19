package com.example.jpa_study.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MainCategoryDTO {

    private Long id;
    private String mainCategoryName;
    private List<ProductsDTO> productsDTOS; // 메인 -> 프로덕트
    private List<SubCategoryDTO> subCategoryDTOS; // 메인 -> 서브카테고리
}
