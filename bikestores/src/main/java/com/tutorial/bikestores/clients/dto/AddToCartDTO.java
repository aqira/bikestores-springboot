package com.tutorial.bikestores.clients.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddToCartDTO {
    private String username;
    private Integer productId;
    private Integer storeId;
}
