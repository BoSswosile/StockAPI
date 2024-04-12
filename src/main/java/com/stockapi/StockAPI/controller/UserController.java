package com.stockapi.StockAPI.controller;

import com.stockapi.StockAPI.model.Role;
import com.stockapi.StockAPI.model.User;
import com.stockapi.StockAPI.services.AuthService;
import com.stockapi.StockAPI.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @GetMapping("getUserRole")
    public ResponseEntity<?> getRole(@RequestHeader("Authorization") String token) {
        token = token.substring(7);
        Optional<User> user = authService.findUserByToken(token);
        if (user.isEmpty()) return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(user.get().getRoles(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("setRole")
    public ResponseEntity<?> setRole(@RequestHeader("Authorization") String token, @RequestBody String userId, @RequestBody Role.RoleName role) {
        token = token.substring(7);
        Optional<User> user = authService.findUserByToken(token);
        if (user.isEmpty()) return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        if (user.get().getRoles().contains("ROLE_ADMIN")) {
            userService.setRole(userId, role.name());
            return new ResponseEntity<>("Role set", HttpStatus.OK);
        }
        return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
    }
}
