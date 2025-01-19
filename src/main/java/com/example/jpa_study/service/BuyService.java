package com.example.jpa_study.service;

import com.example.jpa_study.dto.BuyDTO;
import com.example.jpa_study.dto.CartDTO;
import com.example.jpa_study.dto.RequestBuyDTO;
import com.example.jpa_study.entity.Buy;
import com.example.jpa_study.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BuyService {
    List<BuyDTO> buyProductsByUser(RequestBuyDTO requestBuyDTO);

    List<BuyDTO> getAllBuyProductsByUser(Long username);

    public default BuyDTO convertToBuyDTO(Buy buy) {
        return BuyDTO.builder()
                .id(buy.getId())
                .buyQuantity(buy.getBuyQuantity())
                .buyPrice(buy.getBuyPrice())
                .productId(buy.getBuyProduct().getId())
                .userId(buy.getUser().getId())
                .totalPrice(buy.getTotalPrice()*buy.getBuyQuantity())
                .build();
    }

}
