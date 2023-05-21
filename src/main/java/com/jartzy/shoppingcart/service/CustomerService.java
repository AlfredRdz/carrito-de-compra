package com.jartzy.shoppingcart.service;

import com.jartzy.shoppingcart.entity.Customer;
import com.jartzy.shoppingcart.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    public List<Customer> getAll(){
        return customerRepository.findAll();
    }

    public Optional<Customer> getById(int id){
        return customerRepository.findById(id);
    }

    public Integer isCustomerPresent(Customer customer){
        Customer customer1 = customerRepository.getCustomerByEmailAndName(customer.getEmail(),customer.getName());
        return customer1 != null ? customer1.getId(): null ;
    }

    @Transactional
    public Customer save(Customer customer){
        return customerRepository.save(customer);
    }
}
