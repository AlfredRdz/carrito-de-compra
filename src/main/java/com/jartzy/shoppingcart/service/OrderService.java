package com.jartzy.shoppingcart.service;

import com.jartzy.shoppingcart.dto.OrderRequest;
import com.jartzy.shoppingcart.dto.OrderResponse;
import com.jartzy.shoppingcart.entity.Customer;
import com.jartzy.shoppingcart.entity.Order;
import com.jartzy.shoppingcart.entity.Product;
import com.jartzy.shoppingcart.entity.ShoppingCart;
import com.jartzy.shoppingcart.repository.OrderRepository;
import com.jartzy.shoppingcart.repository.ProductRepository;
import com.jartzy.shoppingcart.utils.DateUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CustomerService customerService;

    public Order getOrderDetail(int orderId) {
        Optional<Order> order = this.orderRepository.findById(orderId);
        return order.isPresent() ? order.get() : null;
    }

    @Transactional
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public float getCartAmount(List<ShoppingCart> shoppingCartList) {

        float totalCartAmount = 0f;
        float singleCartAmount = 0f;
        int availableQuantity = 0;

        for (ShoppingCart cart : shoppingCartList) {

            int productId = cart.getProductId();
            Optional<Product> product = productRepository.findById(productId);
            if (product.isPresent()) {
                Product product1 = product.get();
                if (product1.getAvailableQuantity() < cart.getQuantity()) {
                    singleCartAmount = product1.getPrice() * product1.getAvailableQuantity();
                    cart.setQuantity(product1.getAvailableQuantity());
                } else {
                    singleCartAmount = cart.getQuantity() * product1.getPrice();
                    availableQuantity = product1.getAvailableQuantity() - cart.getQuantity();
                }
                totalCartAmount = totalCartAmount + singleCartAmount;
                product1.setAvailableQuantity(availableQuantity);
                availableQuantity=0;
                cart.setProductName(product1.getName());
                cart.setAmount(singleCartAmount);
                productRepository.save(product1);
            }
        }
        return totalCartAmount;
    }

    @Transactional
    public OrderResponse placeOrder(OrderRequest orderRequest){
        OrderResponse orderResponse = new OrderResponse();
        float amount = getCartAmount(orderRequest.getCartItems());

        Customer customer = new Customer(orderRequest.getCustomerName(), orderRequest.getCustomerEmail());
        Integer customerIdFromDb = customerService.isCustomerPresent(customer);
        if (customerIdFromDb != null) {
            customer.setId(customerIdFromDb);
        }else{
            customer = customerService.save(customer);
        }
        Order order = new Order(orderRequest.getOrderDescription(), customer, orderRequest.getCartItems());
        order = saveOrder(order);

        orderResponse.setAmount(amount);
        orderResponse.setDate(DateUtil.getCurrentDateTime());
        orderResponse.setInvoiceNumber(new Random().nextInt(1000));
        orderResponse.setOrderId(order.getId());
        orderResponse.setOrderDescription(orderRequest.getOrderDescription());

        return orderResponse;
    }
}
