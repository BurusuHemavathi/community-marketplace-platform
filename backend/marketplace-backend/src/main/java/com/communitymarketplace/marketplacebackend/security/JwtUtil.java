package com.communitymarketplace.marketplacebackend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET_KEY =
            "myverysecuresecretkeymyverysecuresecretkey12345";

    // GENERATE TOKEN

    public String generateToken(String email) {

        return Jwts.builder()

                .setSubject(email)

                .setIssuedAt(new Date())

                .setExpiration(
                        new Date(
                                System.currentTimeMillis()
                                        + 1000 * 60 * 60 * 10
                        )
                )

                .signWith(
                        SignatureAlgorithm.HS256,
                        SECRET_KEY
                )

                .compact();
    }

    // EXTRACT USERNAME

    public String extractUsername(String token) {

        return extractClaims(token).getSubject();
    }

    // EXTRACT CLAIMS

    private Claims extractClaims(String token) {

        return Jwts.parser()

                .setSigningKey(SECRET_KEY)

                .parseClaimsJws(token)

                .getBody();
    }

    // VALIDATE TOKEN

    public boolean validateToken(String token) {

        try {

            extractUsername(token);

            return true;

        } catch (Exception e) {

            return false;
        }
    }
}