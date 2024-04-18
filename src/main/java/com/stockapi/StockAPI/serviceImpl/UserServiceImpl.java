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
import java.util.List;
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
    public void setRole(String userId, Role role) {
        Optional<User> user = findById(userId);
        if (user.isEmpty()) return;
        if (user.get().getRoles().contains(role)) return;
        if (roleRepo.findByRoleName(role.getRoleName()).isEmpty()) return;
        List<Role> roles = user.get().getRoles();
        roles.clear();
        roles.add(role);
        userRepo.save(user.get());
    }

    @Override
    public Optional<User> findById(String userId) {
        return userRepo.findById(userId);
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }
}
