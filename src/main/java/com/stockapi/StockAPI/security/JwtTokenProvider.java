package com.stockapi.StockAPI.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    public String generateToken(Authentication authentication) {

        String name = authentication.getName();
        String role = authentication.getAuthorities().stream().findFirst().get().getAuthority();
        Date currentDate = new Date();

        Date expireDate = new Date(currentDate.getTime() + 1000 * 60 * 60 * 24);

        String token = Jwts.builder().claim("name", name).claim("role", role).issuedAt(new Date()).expiration(expireDate).signWith(key()).compact();

        return token;
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public boolean validateToken(String token) {
        Jwts.parser().verifyWith((SecretKey) key()).build().parse(token);
        return true;

    }

    public String getUsername(String token) {
        return Jwts.parser().verifyWith((SecretKey) key()).build().parseSignedClaims(token).getPayload().getSubject();
    }
}