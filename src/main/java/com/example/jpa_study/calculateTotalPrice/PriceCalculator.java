package com.example.jpa_study.calculateTotalPrice;

public class PriceCalculator {
    public static Long calculateTotalPrice(Long price, Long quantity) {
        return price * quantity;
    }
}
