package com.shopease.shop_ease_backend.service.user;

import com.shopease.shop_ease_backend.domain.User;
import com.shopease.shop_ease_backend.dto.LoginRequest;
import com.shopease.shop_ease_backend.repository.UserRepository;
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

    public User save(User user) { // 회원가입
        user.setPassword(passwordEncoder.encode(user.getPassword())); // password 암호화
        return userRepository.save(user);
    }

    public User userLogin(LoginRequest loginRequest) throws Exception {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = userRepository.findByEmail(loginRequest.getEmail());
        if (user == null) {
            throw new Exception("Invalid email or password");
        }
        return user;
    }

    public void registerUser(User user) throws Exception{
        if(findByEmail(user.getEmail()) != null){
            throw new Exception("Email is already in use");
        }
        save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
