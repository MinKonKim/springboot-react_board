package com.minkon.board.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;


// JWT : 전자 서명이 된 토큰
// JSON 형태로 구성된 토큰
// {header}.{payload}.{signature}


@Service
public class TokenProvider {


    // JWT 생성 및 검증을 위한 키
    private static final String SECURITY_KEY = "jwtseckey!@.aaa.bb";

    private final long validityInMilliseconds = 3600000; // 토큰 유효 시간 (1시간)

    public String createToken(Map<String, Object> claims , String username) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, SECURITY_KEY)
                .compact();
    }

    public String createRefreshToken(String username) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, SECURITY_KEY)
                .compact();
    }
}
