package com.stockapi.StockAPI.serviceImpl;

import com.stockapi.StockAPI.model.Role;
import com.stockapi.StockAPI.model.User;
import com.stockapi.StockAPI.repositories.RoleRepo;
import com.stockapi.StockAPI.repositories.UserRepo;
import com.stockapi.StockAPI.security.JwtService;
import com.stockapi.StockAPI.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class UserImplem implements UserService {
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    JwtService jwtService;

    @Override
    public User register(User user) {
        Optional<Role> role = roleRepo.findByRoleName(Role.RoleName.VIEWER.name());
        if(role.isEmpty()) return null;
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.getRoles().add(role.get());
        return userRepo.save(user);
    }

    @Override
    public Map<String, Object> login(String email, String password) throws NoSuchAlgorithmException {
        Optional<User> user = userRepo.findByEmail(email);
        if (user.isEmpty() || !bCryptPasswordEncoder.matches(password, user.get().getPassword()))
            return null;
        Map<String, Object> result = new HashMap<>();
        result.put("jwt", jwtService.generateToken(user.get()));
        result.put("email", email);
        List<String> roles = new ArrayList<>();
        user.get().getRoles().forEach(role -> roles.add(role.getRoleName()));
        result.put("roles", roles);
        return result;
    }
}
