package com.example.jpa_study.service;

import com.example.jpa_study.dto.UserDTO;
import com.example.jpa_study.entity.User;
import com.example.jpa_study.jwt.CustomUserDetails;
import com.example.jpa_study.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor

public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public void saveUser(UserDTO userDTO) {
        String username = userDTO.getUsername();
        String requestRole = userDTO.getRole();
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            throw new RuntimeException("이미 존재하는  사용자 입니다");
        }
        User user = toEntity(userDTO);
        //회원가입때 SHOP_MANAGER 권한으로 신청한 경우
        if ("SHOP_MANAGER".equals(requestRole)) {
            user.setRole(User.UserRole.PENDING_MANAGER); // 승인 전에는 PENDING_MANAGER 로 user 엔티티에 저장됨
        } else {
            user.setRole(User.UserRole.USER);
        }
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public UserDTO getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("유저가 없습니다"));
        return toDTO(user);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }


    @Override // 로그인처리
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User " + username + " not found");
        }
        return new CustomUserDetails(userOptional.get());
    }


    @Override
    public void approveShopManager(String managerUsername, String adminUsername) {
        User admin = userRepository.findByUsername(adminUsername)
                .orElseThrow(() -> new RuntimeException("관리자 아님 꺼지삼"));

        if (admin.getRole() != User.UserRole.ADMIN) {
            throw new RuntimeException("꺼져ㅋ");
        }

        User manager = userRepository.findByUsername(managerUsername)
                .orElseThrow(() -> new RuntimeException("회원이 아님"));
        if (manager.getRole() != User.UserRole.PENDING_MANAGER) {
            throw new RuntimeException("승인 대상 아님 ㄲㅈ");
        }
        manager.setRole(User.UserRole.SHOP_MANAGER);
        userRepository.save(manager);
    }
}

