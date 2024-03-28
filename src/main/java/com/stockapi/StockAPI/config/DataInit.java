package com.stockapi.StockAPI.config;

import com.stockapi.StockAPI.model.Role;
import com.stockapi.StockAPI.repositories.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInit implements CommandLineRunner {

    @Autowired
    RoleRepo roleRepo;

    @Override
    public void run(String... args) throws Exception {
        for (Role.RoleName roleName : Role.RoleName.values()) {
            if (!roleRepo.existsByRoleName(roleName.name())) {
                roleRepo.save(new Role(null, roleName.name()));
            }
        }
    }
}