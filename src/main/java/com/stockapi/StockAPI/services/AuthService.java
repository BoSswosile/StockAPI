package com.stockapi.StockAPI.services;

import com.stockapi.StockAPI.model.LoginDto;
import com.stockapi.StockAPI.model.Role;
import com.stockapi.StockAPI.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface AuthService {
    String login(LoginDto loginDto);

    Role getRole(String token);

    Optional<User> findUserByToken(String token);
}
