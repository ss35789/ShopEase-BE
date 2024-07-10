package com.shopease.shop_ease_backend.controller;

import com.shopease.shop_ease_backend.dto.LoginRequest;
import com.shopease.shop_ease_backend.dto.UserDTO;
import com.shopease.shop_ease_backend.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {
        try {
            userService.save(userDTO);
            return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
//      로그인 부분은 SecurityFilterChain의 LoginFilter에서 처리됨으로 필요없음
//    @PostMapping("/login")
//    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
//        try {
//            UserDTO loggedInUser = userService.findByEmail(loginRequest.getEmail());
//            return new ResponseEntity<>(loggedInUser, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>("Invalid email or password", HttpStatus.UNAUTHORIZED);
//        }
//    }
}
