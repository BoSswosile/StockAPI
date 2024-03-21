package com.stockapi.StockAPI.repositories;

import com.stockapi.StockAPI.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends MongoRepository<Role, Long> {
    Optional<Role> findByRoleName(String roleName);
}
