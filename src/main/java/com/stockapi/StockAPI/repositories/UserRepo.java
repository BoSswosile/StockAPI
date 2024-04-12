package com.stockapi.StockAPI.repositories;

import com.stockapi.StockAPI.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends MongoRepository<User, Long>{

    User save(User user);

    Optional<User> findByEmail(String email);

    Optional<User> findById(String userId);
}
