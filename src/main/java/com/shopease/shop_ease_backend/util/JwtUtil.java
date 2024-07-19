package com.shopease.shop_ease_backend.util;

import com.shopease.shop_ease_backend.dto.modelDTO.UserDTO;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * JWT 관련 메서드를 제공하는 클래스.
 * JWT 토큰 생성, 파싱, 검증 등의 기능을 제공합니다.
 */
@Slf4j
@Component
public class JwtUtil {

    private final Key key;
    private final long accessTokenExpTime;

    public JwtUtil(
            @Value("${jwt.secret}") String secretKey,
            @Value("${jwt.expiration_time}") long accessTokenExpTime
    ) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.accessTokenExpTime = accessTokenExpTime;
    }

    /**
     * Access Token 생성
     * @param user 사용자 정보
     * @return Access Token String
     */
    public String createAccessToken(UserDTO user) {
        return createToken(user, accessTokenExpTime);
    }

    /**
     * JWT 생성
     * @param user 사용자 정보
     * @param expireTime 만료 시간 (밀리초)
     * @return JWT String
     */
    private String createToken(UserDTO user, long expireTime) {
        Claims claims = Jwts.claims();
        claims.put("email", user.getEmail());
        claims.put("password", user.getPassword());

        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime tokenValidity = now.plusSeconds(expireTime / 1000);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date.from(now.toInstant()))
                .setExpiration(Date.from(tokenValidity.toInstant()))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Token에서 User ID 추출
     * @param token JWT 토큰
     * @return User ID
     */
    public Long getUserId(String token) {
        return parseClaims(token).get("userKey", Long.class);
    }

    /**
     * JWT 검증
     * @param token JWT 토큰
     * @return 토큰 유효 여부
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
        }
        return false;
    }

    /**
     * JWT Claims 추출
     * @param accessToken JWT 토큰
     * @return JWT Claims
     */
    public Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}
