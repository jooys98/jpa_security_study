package com.example.jpa_study.service;

import com.example.jpa_study.dto.ProductsDTO;
import com.example.jpa_study.entity.Likes;
import com.example.jpa_study.entity.Products;
import com.example.jpa_study.entity.User;
import com.example.jpa_study.repository.LikesRepository;
import com.example.jpa_study.repository.ProductsRepository;
import com.example.jpa_study.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
@Transactional
@RequiredArgsConstructor // 이거 한번만 더 까먹으면 진짜 흑우 ㅇㅈ
public class LikesServiceImpl implements LikesService {
    private final UserRepository userRepository;
    private final LikesRepository likesRepository;
    private final ProductsRepository productsRepository;



    @Override
    public List<ProductsDTO> getLikedListByUserId(Long userId) {
        try {
            User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("유저가 없어요"));
            Long LikedUserId = user.getId();
            List<Products> likedProducts = likesRepository.findLikedProductsByUserId(LikedUserId);
            return likedProducts.stream().map(this::convertToProductsDTO)
                    .collect(Collectors.toList());
        }catch (Exception e){
            throw new RuntimeException("좋아요 프로덕트 조회 실패 ㅜㅜ");
        }
    }

    @Override
    public boolean changeLikes(Long userId, Long productId) {
        try {
            boolean likesResult = likesRepository.existsByUserIdAndProductId(userId, productId);
if(likesResult){
    likesRepository.deleteUserProducts(userId,productId);
    return false;
}else {
    User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("유저아이디 조회 실패"));
    Products products = productsRepository.findById(productId).orElseThrow(()->new RuntimeException("프로덕트 아이디 없음 "));
    Likes likes = Likes.createLikes(user,products);
    likesRepository.save(likes);
    return true;
}
        } catch (Exception e) {
            throw new RuntimeException("좋아요 토글 실패 ㅜㅜ");
        }
    }


}
