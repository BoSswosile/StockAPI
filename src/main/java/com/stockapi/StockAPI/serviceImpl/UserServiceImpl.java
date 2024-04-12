package com.stockapi.StockAPI.serviceImpl;

import com.stockapi.StockAPI.model.Role;
import com.stockapi.StockAPI.model.User;
import com.stockapi.StockAPI.repositories.RoleRepo;
import com.stockapi.StockAPI.repositories.UserRepo;
import com.stockapi.StockAPI.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User register(User user) throws NoSuchAlgorithmException {
        Optional<Role> role = roleRepo.findByRoleName(Role.RoleName.ROLE_VIEWER.name());
        if (role.isEmpty()) return null;
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.getRoles().add(role.get());
        return userRepo.save(user);
    }

    @Override
    public Map<String, Object> login(String email, String password) {
        Optional<User> user = userRepo.findByEmail(email);
        if (user.isEmpty() || !bCryptPasswordEncoder.matches(password, user.get().getPassword())) return null;
        Map<String, Object> result = new HashMap<>();
        // result.put("jwt", jwtService.generateToken(user.get()));
        result.put("email", user.get().getEmail());
        result.put("name", user.get().getUsername());
        return result;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public void setRole(String userId, String roleName) {
        Optional<User> user = userRepo.findById(Long.valueOf(userId));
        if (user.isEmpty()) return;
        Optional<Role> role = roleRepo.findByRoleName(roleName);
        if (role.isEmpty()) return;
        user.get().getRoles().add(role.get());
        userRepo.save(user.get());
    }
}
