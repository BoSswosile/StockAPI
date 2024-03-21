package com.stockapi.StockAPI.repositories;

import com.stockapi.StockAPI.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.security.ProtectionDomain;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    Product save(Product product);

    /*@Query("{name:'?0'}")
    Product findItemByName(String name);*/

    Optional<Product> findByName(String name);

    long count();
    void deleteById(Long id);

    void delete(Product product);
}
