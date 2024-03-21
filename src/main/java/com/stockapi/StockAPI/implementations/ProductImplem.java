package com.stockapi.StockAPI.implementations;

import com.stockapi.StockAPI.model.Product;
import com.stockapi.StockAPI.model.User;
import com.stockapi.StockAPI.repositories.ProductRepository;
import com.stockapi.StockAPI.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductImplem implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
}
