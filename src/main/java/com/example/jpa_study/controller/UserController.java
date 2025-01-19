package com.example.jpa_study.controller;


import com.example.jpa_study.dto.UserDTO;
import com.example.jpa_study.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @GetMapping("/id")
    public ResponseEntity<UserDTO> getUser(@RequestParam Long id) {

        return ResponseEntity.ok(userService.getUser(id));
    }

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody UserDTO userDTO) {
        String password = passwordEncoder.encode(userDTO.getPassword());
        userDTO.setPassword(password);
        userService.saveUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("가입성공");
    } //회원가입때 권한 설정

    @GetMapping("/check")
    public ResponseEntity<Boolean> checkUsername(@RequestParam String username) {
        return ResponseEntity.ok(userService.existsByUsername(username));
    }

    @PostMapping("/manager") // 매니저의 승인 api
    public ResponseEntity<String> managerUser(
            @RequestParam String managerUsername,
            @AuthenticationPrincipal UserDetails adminDetails) {
        userService.approveShopManager(managerUsername, adminDetails.getUsername());
        return ResponseEntity.ok("매니저 ㅇㅋ");
    }
}
