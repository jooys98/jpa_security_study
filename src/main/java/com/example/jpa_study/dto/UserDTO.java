package com.example.jpa_study.dto;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private LocalDate birthday;
    private String phoneNumber;
    private String role;
}
