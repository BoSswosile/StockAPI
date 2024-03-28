package com.stockapi.StockAPI.services;

import com.stockapi.StockAPI.model.LoginDto;

public interface AuthService {
    String login(LoginDto loginDto);
}
