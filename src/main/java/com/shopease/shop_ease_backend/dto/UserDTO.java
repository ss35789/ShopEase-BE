package com.shopease.shop_ease_backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDTO {
    private Long userKey;

    @NotBlank(message = "User name is mandatory")
    private String userName;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotBlank(message = "Password is mandatory")
    private String password;

    @NotBlank(message = "Telephone number is mandatory")
    private String tel;
    public UserDTO(){};

    public UserDTO(String userName, String email, String password, String tel) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.tel = tel;
    }

}
