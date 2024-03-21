package com.stockapi.StockAPI.services;

import com.stockapi.StockAPI.model.Product;
import com.stockapi.StockAPI.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {

    List<Product> getProduct();

    Product createProduct(Product product);
}
