package com.shopease.shop_ease_backend.service.user;

import com.shopease.shop_ease_backend.domain.User;
import com.shopease.shop_ease_backend.dto.UserDTO;
import com.shopease.shop_ease_backend.repository.UserRepository;
import com.shopease.shop_ease_backend.exception.CustomException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserDTO save(UserDTO userDTO) { // 회원가입
        if (userRepository.findByEmail(userDTO.getEmail()) != null) {
            throw new CustomException("Email is already in use");
        }
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword())); // 암호화된 비밀번호 설정
        userDTO.setRole("ROLE_USER");
        User user = convertToEntity(userDTO);
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }


    public UserDTO findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user != null ? convertToDTO(user) : null;
    }

    private User convertToEntity(UserDTO userDTO) {
        return new User(
                userDTO.getUserKey(),
                userDTO.getUserName(),
                userDTO.getEmail(),
                userDTO.getPassword(),
                userDTO.getTel(),
                userDTO.getRole(),
                null,
                null
        );
    }

    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserKey(user.getUserKey());
        userDTO.setUserName(user.getUserName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setTel(user.getTel());
        userDTO.setRole(user.getRole());
        return userDTO;
    }
}
