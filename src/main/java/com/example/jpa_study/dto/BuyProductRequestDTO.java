package com.example.jpa_study.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BuyProductRequestDTO {
    private Long productId;
    private String productName;
    private Long productPrice;
    private Long productQuantity;
    private Long totalPrice;


}
