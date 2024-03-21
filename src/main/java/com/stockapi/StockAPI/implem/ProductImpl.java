package com.stockapi.StockAPI.implem;

import com.stockapi.StockAPI.model.Product;
import com.stockapi.StockAPI.repositories.ProductRepository;
import com.stockapi.StockAPI.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.insert(product);
    }
}
