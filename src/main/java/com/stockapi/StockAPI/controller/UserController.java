package com.stockapi.StockAPI.controller;

import com.stockapi.StockAPI.model.Role;
import com.stockapi.StockAPI.model.User;
import com.stockapi.StockAPI.services.AuthService;
import com.stockapi.StockAPI.services.RoleService;
import com.stockapi.StockAPI.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @Autowired
    private RoleService roleService;

    @GetMapping("getUserRole")
    public ResponseEntity<?> getUserRole(@RequestHeader("Authorization") String token) {
        token = token.substring(7);
        Optional<User> user = authService.findUserByToken(token);
        if (user.isEmpty()) return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(user.get().getRoles(), HttpStatus.OK);
    }


    @PostMapping("setRole/{userId}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<?> setRole(@RequestBody Role roleName, @PathVariable String userId){
        Optional<User> user = userService.findById(userId);
        if (user.isEmpty()) return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        Role role = roleService.getRoleByName(roleName.getRoleName());
        userService.setRole(userId, role);
        Optional<User> newUser = userService.findById(userId);
        return new ResponseEntity<>(newUser.get().getRoles(), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @GetMapping("getAllUsers")
    public ResponseEntity<?> getAllUser(@RequestHeader("Authorization") String token) {
        List<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
