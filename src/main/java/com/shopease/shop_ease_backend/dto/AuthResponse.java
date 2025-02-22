package com.shopease.shop_ease_backend.dto;

import com.shopease.shop_ease_backend.dto.modelDTO.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String accessToken;
    private UserDTO user;
}
