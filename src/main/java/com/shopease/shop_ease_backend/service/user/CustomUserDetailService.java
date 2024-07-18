package com.shopease.shop_ease_backend.service.user;

import com.shopease.shop_ease_backend.domain.User;
import com.shopease.shop_ease_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), Collections.singletonList(authority));
    }
}
