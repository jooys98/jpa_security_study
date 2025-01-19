package com.example.jpa_study.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

@Override
public void commence(HttpServletRequest request, HttpServletResponse response,
                     org.springframework.security.core.AuthenticationException authException) throws IOException, IOException {
    Map<String, Object> responseData = new HashMap<>();
    //클라에 전송할 response 를 map 형태로 구성
    responseData.put("error", "Unauthorized");
    responseData.put("message", "먼저 로그인후 시도해주세요");
//map 을 제이슨으로 변환하고 401에러와 함께 전달
    ObjectMapper mapper = new ObjectMapper();
    String jsonmessage = mapper.writeValueAsString(responseData);

    response.setContentType("application/json"); // JSON 형식으로 응답
    response.setStatus(401);// 401 Unauthorized 상태 코드 설정
    response.setCharacterEncoding("UTF-8");  // 한글 깨짐 방지
    response.getWriter().write(jsonmessage); // 응답 본문에 JSON 메시지 작성


}
}
