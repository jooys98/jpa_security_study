package com.example.jpa_study.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LikesDTO {
private Long id;
private Long userId;
private Long productId;
}
