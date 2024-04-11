package com.stockapi.StockAPI.services;

import com.stockapi.StockAPI.model.User;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

@Service
public interface UserService {
    User register(User entity) throws NoSuchAlgorithmException;

    Map<String, Object> login(String email, String password);
}
