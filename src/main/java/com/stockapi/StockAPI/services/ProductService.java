package com.stockapi.StockAPI.services;

import com.stockapi.StockAPI.model.Product;
import com.stockapi.StockAPI.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public interface ProductService {

    List<Product> getProducts();

    Product createProduct(Product product);

    void deleteById(String id);

    Product update(Optional<Product> product, Product updatedProduct);

    Optional<Product> findById(String id);
}
