package com.stockapi.StockAPI.serviceImpl;

import com.stockapi.StockAPI.model.Role;
import com.stockapi.StockAPI.repositories.RoleRepo;
import com.stockapi.StockAPI.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepo roleRepo;
    @Override
    public Role getRoleByName(String roleName) {
        return roleRepo.findByRoleName(roleName).orElse(null);
    }
}
