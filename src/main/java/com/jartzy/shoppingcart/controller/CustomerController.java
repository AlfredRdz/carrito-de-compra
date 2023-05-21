package com.jartzy.shoppingcart.controller;

import com.jartzy.shoppingcart.entity.Customer;
import com.jartzy.shoppingcart.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/getAllCustomers")
    public ResponseEntity<List<Customer>> getAllCustomers(){
        return ResponseEntity.ok(customerService.getAll());
    }

    @GetMapping(value = "/getCustomerById/{id}")
    public ResponseEntity<Optional<Customer>> getAllCustomers(@PathVariable Integer id){
        return ResponseEntity.ok(customerService.getById(id));
    }

    @PostMapping()
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer){
        return new ResponseEntity<>(customerService.save(customer), HttpStatus.CREATED);
    }
}
