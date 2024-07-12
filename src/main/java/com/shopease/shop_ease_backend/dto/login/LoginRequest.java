package com.shopease.shop_ease_backend.dto.login;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
