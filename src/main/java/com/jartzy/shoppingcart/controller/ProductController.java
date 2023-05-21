package com.jartzy.shoppingcart.controller;

import com.jartzy.shoppingcart.dto.ProductQuantityRequest;
import com.jartzy.shoppingcart.entity.Product;
import com.jartzy.shoppingcart.service.CustomerService;
import com.jartzy.shoppingcart.service.OrderService;
import com.jartzy.shoppingcart.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Product")

public class ProductController {

    private OrderService orderService;
    private ProductService productService;
    private CustomerService customerService;

    public ProductController(OrderService orderService, ProductService productService, CustomerService customerService) {
        this.orderService = orderService;
        this.productService = productService;
        this.customerService = customerService;
    }

    @GetMapping(value = "/getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> productList = productService.getAllProducts();

        return ResponseEntity.ok(productList);
    }

    @GetMapping(value = "/getProductById/{id}")
    public ResponseEntity<Product> getAllProducts(@PathVariable Integer id) {
        Product productList = productService.getById(id);

        return ResponseEntity.ok(productList);
    }

    @PostMapping()
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {

        return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.CREATED);
    }

    @PutMapping(value = "/updateQuantity/{id}")
    public ResponseEntity<Product> updateQuantityProduct(@PathVariable Integer id,@Valid @RequestBody ProductQuantityRequest product) {
        return new ResponseEntity<>(productService.updateQuantity(id, product), HttpStatus.CREATED);
    }
}
