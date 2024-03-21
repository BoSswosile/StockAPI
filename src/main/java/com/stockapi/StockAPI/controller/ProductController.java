package com.stockapi.StockAPI.controller;

import com.stockapi.StockAPI.model.Product;
import com.stockapi.StockAPI.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.Any;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getProduct() {
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody Object object) {
        Product product = new ModelMapper().map(object, Product.class);
        return new ResponseEntity<>(productService.createProduct(product), HttpStatus.CREATED);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        productService.deleteById(id);
        return new ResponseEntity<>("Suppression successful", HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> getProductById(@PathVariable String id) {
        Optional<Product> product = productService.findById(id);
        if(product.isEmpty()){
            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND
            );
        }else{
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Product> update(@PathVariable String id, @RequestBody Product updatedProduct){
        Optional<Product> product = productService.findById(id);
        if(product.isEmpty()){
            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND
            );
        }else{
            return new ResponseEntity<>(
                    productService.update(product,updatedProduct), HttpStatus.OK
            );
        }
    }

    @GetMapping("/ByRefProduct/{ref}")
    public ResponseEntity<Optional<Product>> findByRefId(String ref){
        Optional<Product> product = productService.findByRefId(ref);
        if(product.isEmpty()){
            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND
            );
        }else{
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
    }
}