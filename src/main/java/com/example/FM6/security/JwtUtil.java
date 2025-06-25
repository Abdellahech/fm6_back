package com.example.FM6.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

    // Token expires in 1 hour
    private static final long EXPIRATION_TIME = 1000 * 60 * 60;

    // Key for signing tokens (in-memory, for demo; use env in prod)
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // ✅ Updated: Generate JWT with email and role
    public static String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact();
    }

    // (Optional) Still keeping the original version if needed
    public static String generateToken(String email) {
        return generateToken(email, "user"); // default role
    }
}
