package com.example.jpa_study.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductsDTO {
    private Long id;
    private String productName;
    private String productDescription;
    private Long productPrice;
    private String subCategoryName;
    private String mainCategoryName;
    private List<LikesDTO> likes;
    private List<BuyDTO> buys;
}
