package com.example.jpa_study.entity;

import com.example.jpa_study.calculateTotalPrice.PriceCalculator;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "buy")
public class Buy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long buyQuantity;
    private Long buyPrice;
    private Long totalPrice;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Products buyProduct;
// db의 fk 이므로 관계 설정시 필수



}
