package com.jartzy.shoppingcart.service;

import com.jartzy.shoppingcart.dto.ProductQuantityRequest;
import com.jartzy.shoppingcart.entity.Product;
import com.jartzy.shoppingcart.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    public Product getById(Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("FIELD_WITH_ID" + id + "DOES_NOT_EXIST"));
    }

    @Transactional
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public Product updateQuantity(int id, ProductQuantityRequest productRequest) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("FIELD_WITH_ID" + id + "DOES_NOT_EXIST"));

        product.setAvailableQuantity(productRequest.getQuantity());
        return productRepository.save(product);
    }
}
