package com.stockapi.StockAPI.serviceImpl;

import com.stockapi.StockAPI.model.LoginDto;
import com.stockapi.StockAPI.model.Role;
import com.stockapi.StockAPI.model.User;
import com.stockapi.StockAPI.security.JwtTokenProvider;
import com.stockapi.StockAPI.services.AuthService;
import com.stockapi.StockAPI.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private AuthenticationManager authenticationManager;

    private UserService userService;

    @Override
    public String login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }

    @Override
    public Role getRole(String token) {
        return jwtTokenProvider.getRoleFromToken(token);
    }

    @Override
    public Optional<User> findUserByToken(String token) {
        String email = jwtTokenProvider.getEmail(token);
        return userService.findByEmail(email);
    }
}
