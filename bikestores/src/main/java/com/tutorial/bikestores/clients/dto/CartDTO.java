package com.tutorial.bikestores.clients.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class CartDTO {
    private final Integer storeId;
    private final String storeName;
    private final List<CartItemDTO> cartItems;

    public CartDTO(Integer storeId, String storeName) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.cartItems = new ArrayList<>();
    }

    public BigDecimal getTotalPrice() {
        BigDecimal result = new BigDecimal(0);
        for (CartItemDTO cartItem : cartItems) {
            result = result.add(cartItem.getListPrice());
        }
        return result;
    }
}
