package com.example.jpa_study.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SubCategoryDTO {
    private Long id;
    private String subCategoryName;
    private List<ProductsDTO> products;
}
