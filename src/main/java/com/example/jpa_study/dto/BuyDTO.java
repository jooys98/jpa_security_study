package com.example.jpa_study.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BuyDTO {
    private Long id;
    private Long userId;
    private Long productId;
    private String productName;
    private Long buyQuantity;
    private Long buyPrice;
    private Long totalPrice;
}
