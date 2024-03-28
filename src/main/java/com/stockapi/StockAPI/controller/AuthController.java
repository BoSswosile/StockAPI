package com.stockapi.StockAPI.controller;

import com.stockapi.StockAPI.model.JwtAuthResponse;
import com.stockapi.StockAPI.model.LoginDto;
import com.stockapi.StockAPI.model.User;
import com.stockapi.StockAPI.repositories.UserRepo;
import com.stockapi.StockAPI.services.AuthService;
import com.stockapi.StockAPI.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;

    @Autowired
    UserRepo userRepo;


    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody User entity) throws NoSuchAlgorithmException {
        User student = userService.register(entity);
        if (student == null) return new ResponseEntity<>("Internal Server error", HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginDto request) throws NoSuchAlgorithmException {
        if (request.getEmail() == null || request.getPassword() == null)
            return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
        Map<String, Object> result = userService.login(request.getEmail(), request.getPassword());
        if (result == null) return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(authService.login(request));
        return new ResponseEntity<>(jwtAuthResponse.getAccessToken(), HttpStatus.OK);
    }
}
