package com.stockapi.StockAPI;

import com.stockapi.StockAPI.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ProductRepository extends MongoRepository<Product, String> {

    @Query("{name:'?0'}")
    Product findItemByName(String name);

    public long count();
    public void deleteById(int id);
}
