package com.example.jpa_study.jwt;

import com.example.jpa_study.jwt.LoginRequestDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            ObjectMapper objectJson = new ObjectMapper();
            LoginRequestDTO loginRequestDTO = objectJson.readValue(request.getInputStream(), LoginRequestDTO.class);
            UsernamePasswordAuthenticationToken authen = new UsernamePasswordAuthenticationToken(
                    loginRequestDTO.getUsername(), loginRequestDTO.getPassword());
            return authenticationManager.authenticate(authen);
        } catch (IOException e) {
            throw new RuntimeException("인증 시도 실패 ");
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        CustomUserDetails userDetails = (CustomUserDetails) authResult.getPrincipal();
        String userName = userDetails.getUsername();
        Long userId = userDetails.getId();
        String role = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining());
//        String role = userDetails.getRole();
        LocalDate birthday = userDetails.getBirthDay();

        String accessToken = this.jwtUtil.CreateJWT("access", userName, role, 60 * 60 * 1000L);
        String refreshToken = this.jwtUtil.CreateJWT("refresh", userName, role, 24 * 60 * 1000L);

         //response 만들기
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("user_id", userId);
        responseData.put("username", userName);
        responseData.put("phone_number", userDetails.getPhoneNumber());
        responseData.put("birthday", birthday);
        responseData.put("role", role);
        // 키 : 벨류 <hashmap 형태 > 변환
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String jsonMessage = objectMapper.writeValueAsString(responseData);
        //hashMap -> json 오브젝트 변환

        response.addHeader("Authorization", "Bearer " + accessToken);
        response.addCookie(createCookie("refresh", refreshToken));
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().write(jsonMessage);
    }

    private Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(24*60*60);
        return cookie;
    }

    @Override
    //인증 실패시 처리하는 메서드
    //AuthenticationException 반환 시
    public void unsuccessfulAuthentication(HttpServletRequest req, HttpServletResponse res,
                                           AuthenticationException failed) throws IOException, ServletException {
        Map<String, String> responseData = new HashMap<>();
        responseData.put("message", "계정정보가 틀립니다.");
        //인증에 실패한 놈들을 또 map 으로 감싸고 인증이 안됐다는 메세지를 넣는다

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonmessage = objectMapper.writeValueAsString(responseData);
        // 인증 실패한 놈들 + 메세지 를 제이슨으로 바꿔서 클라에 보낼 준비
        res.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().write(jsonmessage);
    }
}
