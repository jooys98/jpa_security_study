package com.example.jpa_study.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RequestBuyDTO {
    private Long userId;
    private List<BuyProductRequestDTO> buyProductRequestDTOList;
    //장바구니에서 주문 처리를 하면 장바구니 물건들이 구매 처리가 된다
}
