package com.example.jpa_study.jwt;

import com.example.jpa_study.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class CustomUserDetails implements UserDetails {
    private final User user;
    //userEntity 에서 사용자 정보를 조회 후 검증하므로 User 엔티티를 참조함


    public CustomUserDetails(User user) {
        this.user = user;
    }
//유저 엔티티의 정보를 토대로 구현체(implement)인 UserDetails 형식으로 객체 생성

    /*userEntity 의 필드들을 조회하는 메서드들 */
    public Long getId() {
        return user.getId(); // userId
    }


    public String getPhoneNumber() {
        return user.getPhoneNumber();
    }

    public LocalDate getBirthDay() {
        return user.getBirthday();
    }


    public String getRole() {
        return user.getRole().name();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (user.getRole() == User.UserRole.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else if (user.getRole() == User.UserRole.SHOP_MANAGER) {
            return List.of(new SimpleGrantedAuthority("ROLE_SHOP_MANAGER"));
        } else if (user.getRole() == User.UserRole.PENDING_MANAGER) {
            return List.of(new SimpleGrantedAuthority("ROLE_PENDING_MANAGER"));
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    //계정 만료 기능 사용 안함
    @Override
    public boolean isAccountNonExpired() {
        return true; // 만료 안됐다는 뜻
    }

    //계정잠금기능 사용 안함
    @Override
    public boolean isAccountNonLocked() {
        return true; //잠금 열려있다는 뜻
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 비밀번호 만료 기능 사용 안함
    }

    @Override
    public boolean isEnabled() {
        return true; // 계정 비활성화 기능을 사용하지 않음
    }
}
