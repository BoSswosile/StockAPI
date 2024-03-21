package com.stockapi.StockAPI.repositories;

import com.stockapi.StockAPI.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    /*@Query("{name:'?0'}")
    Product findItemByName(String name);*/
    List<Product> findAll();
    //get all products
    //create product
    Product insert(Product product);

    void deleteById(String id);

    Optional<Product> findById(String id);
}
