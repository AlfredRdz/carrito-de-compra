package com.jartzy.shoppingcart.dto;

import com.jartzy.shoppingcart.entity.ShoppingCart;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    @NotBlank(message = "you need write a description")
    private String orderDescription;

    @NotEmpty(message = "Minimun 1 Product")
    private List<ShoppingCart> cartItems;

    @NotBlank(message = "You must write your email")
    @Email(message = "You need to write your email")
    private String customerEmail;

    @NotBlank(message = "You need to write your name")
    private String customerName;
}
