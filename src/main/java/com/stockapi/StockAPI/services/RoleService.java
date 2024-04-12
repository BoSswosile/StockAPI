package com.stockapi.StockAPI.services;

import com.stockapi.StockAPI.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService{

    Role getRoleByName(String roleName);

    List<Role> getRoles();
}
