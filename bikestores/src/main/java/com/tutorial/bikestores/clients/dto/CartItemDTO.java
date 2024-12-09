package com.tutorial.bikestores.clients.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemDTO {
    private final Integer productId;
    private final String productName;
    private final BigDecimal listPrice;
}
