package com.example.jpa_study.controller;

import com.example.jpa_study.dto.BuyDTO;
import com.example.jpa_study.dto.CartDTO;
import com.example.jpa_study.dto.RequestBuyDTO;
import com.example.jpa_study.entity.Buy;
import com.example.jpa_study.service.BuyService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buy")
@RequiredArgsConstructor
public class BuyController {

    private final BuyService buyService;

    @GetMapping("/{username}") // 유저의 구매 정보  정보 조회
    public ResponseEntity<List<BuyDTO>> findAllBuyProductsByUser(@PathVariable Long username) {
        List<BuyDTO> buyListByUser = buyService.getAllBuyProductsByUser(username);
        return ResponseEntity.ok(buyListByUser);
    }

    @PostMapping // 구매 로직
    public ResponseEntity<List<BuyDTO>> getBuyProducts(@RequestBody RequestBuyDTO requestBuyDTO) {
     List<BuyDTO> orderedProducts = buyService.buyProductsByUser(requestBuyDTO);
      return ResponseEntity.ok(orderedProducts);
    }

}
