package com.example.jpa_study.service;

import com.example.jpa_study.dto.UserDTO;
import com.example.jpa_study.entity.User;

import java.time.LocalDate;

import static java.lang.String.valueOf;

public interface UserService {




void saveUser(UserDTO userDTO);

default UserDTO toDTO(User user) {
    return UserDTO.builder()
            .id(user.getId())
            .username(user.getUsername())
            .password(user.getPassword())
            .phoneNumber(user.getPhoneNumber())
            .birthday(LocalDate.parse(String.valueOf(user.getBirthday())))
            .build();
}

    default User toEntity(UserDTO userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .phoneNumber(userDTO.getPhoneNumber())
                .birthday(userDTO.getBirthday())
                .build();
    }

    UserDTO getUser(Long id);

    Boolean existsByUsername(String username);



    public void approveShopManager(String managerUsername, String adminUsername);
}
