package com.stockapi.StockAPI.services;

import com.stockapi.StockAPI.model.Role;
import org.springframework.stereotype.Service;

@Service
public interface RoleService{

    Role getRoleByName(String roleName);

}
