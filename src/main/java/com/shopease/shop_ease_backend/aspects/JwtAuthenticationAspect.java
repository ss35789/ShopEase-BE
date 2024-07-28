package com.shopease.shop_ease_backend.aspects;

import com.shopease.shop_ease_backend.util.JwtUtil;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletRequest;

@Aspect
@Component
public class JwtAuthenticationAspect {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private HttpServletRequest request;



    // 특정 URL 패턴 및 HTTP 메서드에 대한 포인트컷 정의
    @Pointcut("within(com.shopease.shop_ease_backend.controller.ItemController)" +
            " && @annotation(org.springframework.web.bind.annotation.DeleteMapping) " +
            "&& execution(* com.shopease.shop_ease_backend.controller.ItemController.delItem(..))")
    public void deleteItemEndpoint() {}

    @Pointcut("within(com.shopease.shop_ease_backend.controller.ItemController) " +
            "&& @annotation(org.springframework.web.bind.annotation.PostMapping)" +
            " && execution(* com.shopease.shop_ease_backend.controller.ItemController.addItem(..))")
    public void addItemEndpoint() {}

    // GET 메서드에 대해 JWT 검증 수행
    @Before("deleteItemEndpoint()")
    public void authenticateGet() throws Exception {
        authenticate();
    }

    // POST 메서드에 대해 JWT 검증 수행
    @Before("addItemEndpoint()")
    public void authenticatePost() throws Exception {
        authenticate();
    }

    public void authenticate() throws Exception {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new Exception("JWT Token is missing");
        }

        String token = authorizationHeader.substring(7);


        if (!jwtUtil.validateToken(token)) {
            throw new Exception("Invalid JWT Token");
        }


    }
}
