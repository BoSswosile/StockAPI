package com.stockapi.StockAPI.controller;

import com.stockapi.StockAPI.model.Product;
import com.stockapi.StockAPI.model.Table;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class StockController {

    @Autowired
    final ModelMapper modelMapper;

    List<Product> products = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Product>> getUsers() {
        List<Product> product = new ArrayList<>();
        products.add(new Product(1, "Product 1", 100, 10, new Table(1, 200, 100, "Red")));
        for (Product prod : products) {
            product.add(modelMapper.map(prod, Product.class));
        }
        return new ResponseEntity<>(
                product,
                HttpStatus.OK
        );
    }
}
