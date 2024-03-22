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
        Product newProduct = product.orElse(new Product());
        newProduct.setName(updatedProduct.getName().isEmpty() ? newProduct.getName() : updatedProduct.getName());
        newProduct.setRefId(updatedProduct.getRefId().isEmpty() ? newProduct.getRefId() : updatedProduct.getRefId());
        newProduct.setPrice(updatedProduct.getPrice() == 0 ? newProduct.getPrice() : updatedProduct.getPrice());
        newProduct.setHeight(updatedProduct.getHeight() == 0 ? newProduct.getHeight() : updatedProduct.getHeight());
        newProduct.setLength(updatedProduct.getLength() == 0 ? newProduct.getLength() : updatedProduct.getLength());
        newProduct.setColor(updatedProduct.getColor().isEmpty() ? newProduct.getColor() : updatedProduct.getColor());
        newProduct.setQuantity(updatedProduct.getQuantity() == 0 ? newProduct.getQuantity() : updatedProduct.getQuantity());
        newProduct.setWidth(updatedProduct.getWidth() == 0 ? newProduct.getWidth() : updatedProduct.getWidth());
        productRepository.deleteById(product.get().getId());
        productRepository.insert(newProduct);
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
