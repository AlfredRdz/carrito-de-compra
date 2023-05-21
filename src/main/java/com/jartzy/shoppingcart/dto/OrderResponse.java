package com.jartzy.shoppingcart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private float amount;
    private int invoiceNumber;
    private String date;
    private String OrderDescription;
    private int orderId;
}
