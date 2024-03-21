package com.stockapi.StockAPI.implem;

import com.stockapi.StockAPI.model.Product;
import com.stockapi.StockAPI.repositories.ProductRepository;
import com.stockapi.StockAPI.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public void deleteById(String id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product update(Optional<Product> product, Product updatedProduct) {
        Product newProduct = product.get();
        if(! updatedProduct.getName().isEmpty()){
            newProduct.setName(updatedProduct.getName());
        }
        if(updatedProduct.getPrice()!=0){
            newProduct.setPrice(updatedProduct.getPrice());
        }
        if(updatedProduct.getHeight()!=0){
            newProduct.setHeight(updatedProduct.getHeight());
        }
        if(updatedProduct.getLength()!=0){
            newProduct.setLength(updatedProduct.getLength());
        }
        if(! updatedProduct.getColor().isEmpty()){
            newProduct.setColor(updatedProduct.getColor());
        }
        if(updatedProduct.getQuantity()!=0){
            newProduct.setQuantity(updatedProduct.getQuantity());
        }
        if(updatedProduct.getWidth()!=0){
            newProduct.setWidth(updatedProduct.getWidth());
        }
        return newProduct;
    }

    @Override
    public Optional<Product> findById(String id) {
        return productRepository.findById(id);
    }

    @Override
    public Optional<Product> findByRefId(String ref) {
        return productRepository.findByRefId(ref);
    }

}
