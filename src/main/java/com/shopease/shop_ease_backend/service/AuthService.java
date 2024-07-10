package com.shopease.shop_ease_backend.service;

import com.shopease.shop_ease_backend.dto.AuthResponse;
import com.shopease.shop_ease_backend.dto.LoginRequest;
import com.shopease.shop_ease_backend.dto.UserDTO;
import com.shopease.shop_ease_backend.util.JwtUtil;
import com.shopease.shop_ease_backend.service.user.UserService;
import com.shopease.shop_ease_backend.util.CookieUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    public AuthResponse login(LoginRequest loginRequest, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDTO loggedInUser = userService.findByEmail(loginRequest.getEmail());
        String accessToken = jwtUtil.createAccessToken(loggedInUser);

        // JWT 토큰을 HTTP-Only 쿠키에 설정
        CookieUtil.create(response, "accessToken", accessToken, true, 60 * 60 * 24, "/");

        return new AuthResponse(accessToken, loggedInUser);
    }

    public void register(UserDTO userDTO) {
        userService.save(userDTO);
    }

    public void logout(HttpServletResponse response) {
        // 로그아웃 시 쿠키 삭제
        CookieUtil.clear(response, "accessToken");
    }
}
