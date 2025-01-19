package com.example.jpa_study.controller;

import com.example.jpa_study.dto.ProductsDTO;
import com.example.jpa_study.entity.Likes;
import com.example.jpa_study.entity.User;
import com.example.jpa_study.repository.ProductsRepository;
import com.example.jpa_study.repository.UserRepository;
import com.example.jpa_study.service.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
public class LikesController {
    private final LikesService likesService;
    private final UserRepository userRepository;
    private final ProductsRepository productsRepository;

    @GetMapping("/list")
    public ResponseEntity<List<ProductsDTO>> getLikesById(@RequestParam Long userId) {
        List<ProductsDTO> likedProducts = likesService.getLikedListByUserId(userId);
        return ResponseEntity.ok(likedProducts);
    }

    @PostMapping
    public ResponseEntity<String> toggleLike(@RequestParam Long userId, @RequestParam Long productId) {
        boolean isLiked = likesService.changeLikes(userId, productId);
        return ResponseEntity.ok(isLiked ? " 좋아요추가 " : "좋아요 취소");

    }
}