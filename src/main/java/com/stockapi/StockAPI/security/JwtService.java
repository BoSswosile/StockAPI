package com.stockapi.StockAPI.security;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    public String generateToken(UserDetails userDetails, Map<String, String> extraClaims) throws NoSuchAlgorithmException {
        return Jwts.builder().setSubject(userDetails.getUsername()).setClaims(extraClaims).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)).signWith(generateSecretKey(), SignatureAlgorithm.HS256).compact();
    }

    public String generateToken(UserDetails userDetails) throws NoSuchAlgorithmException {
        return generateToken(userDetails, new HashMap<>());
    }

    private static Key generateSecretKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
        SecureRandom secureRandom = new SecureRandom();
        keyGenerator.init(secureRandom);
        return keyGenerator.generateKey();
    }
}