package com.example.jpa_study.service;

import com.example.jpa_study.calculateTotalPrice.PriceCalculator;
import com.example.jpa_study.dto.BuyDTO;
import com.example.jpa_study.dto.BuyProductRequestDTO;
import com.example.jpa_study.dto.CartDTO;
import com.example.jpa_study.dto.RequestBuyDTO;
import com.example.jpa_study.entity.Buy;
import com.example.jpa_study.entity.Products;
import com.example.jpa_study.entity.User;
import com.example.jpa_study.repository.BuyRepository;
import com.example.jpa_study.repository.ProductsRepository;
import com.example.jpa_study.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class BuyServiceImpl implements BuyService {
    private final BuyRepository buyRepository;
    private final ProductsRepository productsRepository;
    private final UserRepository userRepository;

    @Override
    public List<BuyDTO> buyProductsByUser(RequestBuyDTO requestBuyDTO) {
        List<BuyDTO> orderedProducts = new ArrayList<>();
//주문 목록 리스트 새로 만들기

        User user = userRepository.findById(requestBuyDTO.getUserId()).orElseThrow
                (() -> new EntityNotFoundException("유저 없음 "));

        for (BuyProductRequestDTO buyProductRequestDTO : requestBuyDTO.getBuyProductRequestDTOList()) {
            Products products = productsRepository.findById(buyProductRequestDTO.getProductId())
                    .orElseThrow(() -> new EntityNotFoundException("상품 존재 안함 "));


            Long totalPrice = PriceCalculator.calculateTotalPrice(
                    buyProductRequestDTO.getProductPrice(),
                    buyProductRequestDTO.getProductQuantity()
            );

            Buy buyOrder = Buy.builder()
                    .user(user)
                    .buyProduct(products)
                    .buyQuantity(buyProductRequestDTO.getProductQuantity())
                    .buyPrice(buyProductRequestDTO.getProductPrice())
                    .totalPrice(totalPrice)
                    .build(); // dto -> 엔티티 -> buy 엔티티에 저장

            Buy saveNewBuyOrder = buyRepository.save(buyOrder);

            orderedProducts.add(BuyDTO.builder()
                    .id(saveNewBuyOrder.getId())
                    .userId(saveNewBuyOrder.getUser().getId())
                    .buyQuantity(saveNewBuyOrder.getBuyQuantity())
                    .buyPrice(saveNewBuyOrder.getBuyPrice())
                    .productId(saveNewBuyOrder.getBuyProduct().getId())
                    .productName(saveNewBuyOrder.getBuyProduct().getProductName())
                    .totalPrice(totalPrice)
                    .build());
        }

        return orderedProducts;

    }

    @Override
    public List<BuyDTO> getAllBuyProductsByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("유저 조회 실패"));
        Long buyUserId = user.getId();
        List<Buy> userBuyList = buyRepository.findByUserId(buyUserId);
        List<BuyDTO> newBuyDtoList = userBuyList.stream()
                .map(this::convertToBuyDTO).collect(Collectors.toList());
        return newBuyDtoList;

    }



//    public Buy convertToBuy(BuyDTO buyDTO) {
//        //dto -> entity
//        User user = userRepository.findById(buyDTO.getUserId())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        Products product = productsRepository.findById(buyDTO.getProductId())
//                .orElseThrow(() -> new RuntimeException("Product not found"));
//
//        return Buy.builder()
//                .id(buyDTO.getId())
//                .buyQuantity(buyDTO.getBuyQuantity())
//                .user(user)
//                .buyProduct(product)
//                .totalPrice(buyDTO.getBuyPrice())
//                .build();
//    }

//    public void processPurchase(BuyProductRequestDTO requestDTO) {
//        Long totalPrice = PriceCalculator.calculateTotalPrice(
//                requestDTO.getProductPrice(),
//                requestDTO.getProductQuantity()
//        );
//}
}