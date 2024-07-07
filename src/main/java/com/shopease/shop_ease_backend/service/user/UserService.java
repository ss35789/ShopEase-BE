package com.shopease.shop_ease_backend.service.user;

import com.shopease.shop_ease_backend.domain.User;
import com.shopease.shop_ease_backend.dto.UserDTO;
import com.shopease.shop_ease_backend.dto.LoginRequest;
import com.shopease.shop_ease_backend.repository.UserRepository;
import com.shopease.shop_ease_backend.exception.CustomException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public UserDTO save(UserDTO userDTO) { // 회원가입
        if (userRepository.findByEmail(userDTO.getEmail()) != null) {
            throw new CustomException("Email is already in use");
        }
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword())); // 암호화된 비밀번호 설정
        User user = convertToEntity(userDTO);
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    public UserDTO userLogin(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = userRepository.findByEmail(loginRequest.getEmail());
        if (user == null) {
            throw new CustomException("Invalid email or password");
        }
        return convertToDTO(user);
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
        return userDTO;
    }
}
