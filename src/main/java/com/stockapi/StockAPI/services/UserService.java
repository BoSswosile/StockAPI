package com.stockapi.StockAPI.services;

import com.stockapi.StockAPI.model.Role;
import com.stockapi.StockAPI.model.User;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public interface UserService {
    User register(User entity) throws NoSuchAlgorithmException;

    Map<String, Object> login(String email, String password);

    Optional<User> findByEmail(String email);

    void setRole(String userId, Role role);

    Optional<User> findById(String userId);

    List<User> findAll();
}
