package com.shopease.shop_ease_backend.controller;

import com.shopease.shop_ease_backend.dto.AuthResponse;
import com.shopease.shop_ease_backend.dto.LoginRequest;
import com.shopease.shop_ease_backend.dto.UserDTO;
import com.shopease.shop_ease_backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class LoginController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {
        try {
            authService.register(userDTO);
            return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        try {
            AuthResponse authResponse = authService.login(loginRequest, response);
            return new ResponseEntity<>(authResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Invalid email or password", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser(HttpServletResponse response) {
        authService.logout(response);
        return new ResponseEntity<>("Logged out successfully", HttpStatus.OK);
    }
}
