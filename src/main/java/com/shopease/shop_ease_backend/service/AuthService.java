package com.shopease.shop_ease_backend.service;

import com.shopease.shop_ease_backend.dto.AuthResponse;
import com.shopease.shop_ease_backend.dto.LoginRequest;
import com.shopease.shop_ease_backend.dto.UserDTO;
import com.shopease.shop_ease_backend.filter.jwt.JwtUtil;
import com.shopease.shop_ease_backend.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    public AuthResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDTO loggedInUser = userService.findByEmail(loginRequest.getEmail());
        String accessToken = jwtUtil.createAccessToken(loggedInUser);

        return new AuthResponse(accessToken, loggedInUser);
    }

    public void register(UserDTO userDTO) {
        userService.save(userDTO);
    }
}
