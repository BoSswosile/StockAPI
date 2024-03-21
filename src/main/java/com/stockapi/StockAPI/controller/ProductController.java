package com.stockapi.StockAPI.controller;

import com.stockapi.StockAPI.model.Product;
import com.stockapi.StockAPI.services.ProductService;
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
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    final ModelMapper modelMapper;

    List<Product> products = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Product>> getProduct() {
        List<Product> products = productService.getProduct();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody Object object) {
        Product product = modelMapper.map(object, Product.class);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        for (Product prod : products) {
            if (prod.getId() == product.getId()) {
                prod.setName(product.getName());
                prod.setPrice(product.getPrice());
                prod.setQuantity(product.getQuantity());
                return new ResponseEntity<>(prod, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable int id) {
        for (Product prod : products) {
            if (prod.getId() == id) {
                products.remove(prod);
                return new ResponseEntity<>(prod, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<Product>> searchProduct(@PathVariable String name) {
        List<Product> product = new ArrayList<>();
        for (Product prod : products) {
            if (prod.getName().contains(name)) {
                product.add(prod);
                return new ResponseEntity<>(product, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        for (Product prod : products) {
            if (prod.getId() == id) {
                return new ResponseEntity<>(prod, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
