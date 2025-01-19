package com.example.jpa_study.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "likes_tbl")
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Products product;

    public static Likes createLikes(User user, Products product) {
        return Likes.builder()
                .user(user)
                .product(product)
                .build();
    } // 유저객체 , 프로덕트 객체 -> 빌더패턴 -> 라이크 엔티티 객체


}
