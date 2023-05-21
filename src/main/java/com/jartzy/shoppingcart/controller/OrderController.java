package com.jartzy.shoppingcart.controller;

import com.jartzy.shoppingcart.dto.OrderRequest;
import com.jartzy.shoppingcart.dto.OrderResponse;
import com.jartzy.shoppingcart.entity.Customer;
import com.jartzy.shoppingcart.entity.Order;
import com.jartzy.shoppingcart.service.CustomerService;
import com.jartzy.shoppingcart.service.OrderService;
import com.jartzy.shoppingcart.utils.DateUtil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/api/Order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/getOrder/{orderId}")
    public ResponseEntity<Order> getOrderDetails(@PathVariable int orderId) {

        Order order = orderService.getOrderDetail(orderId);
        return ResponseEntity.ok(order);
    }

    @PostMapping("/placeOrder")
    public ResponseEntity<OrderResponse> placeOrder(@Valid @RequestBody OrderRequest orderRequest) {

        return new ResponseEntity<>(orderService.placeOrder(orderRequest), HttpStatus.CREATED);
    }
}
