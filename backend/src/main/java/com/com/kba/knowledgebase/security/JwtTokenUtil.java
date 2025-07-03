package com.com.kba.knowledgebase.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;
import javax.crypto.SecretKey;

@Component
public class JwtTokenUtil {

    // 🔐 Secret key must be at least 256 bits (32 characters for HS512)
	private static final String SECRET = "X0&lkwQ!Fz7@Vd#92KxsT3p$MnhGAwYcBeLZjR5uqEHv6IoUCNbJPytxDfKLsMg8";
    private static final long EXPIRATION_TIME = 86400000; // 1 day

    private final SecretKey secretKey = Keys.hmacShaKeyFor(SECRET.getBytes());

    // ✅ Generate JWT token
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }

    // ✅ Get username from token
    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // ✅ Validate JWT
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
