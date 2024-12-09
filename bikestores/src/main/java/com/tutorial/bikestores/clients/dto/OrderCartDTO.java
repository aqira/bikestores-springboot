package com.tutorial.bikestores.clients.dto;

import lombok.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderCartDTO {
    private String username;
    private Integer storeId;
    private Integer staffId = 1; //masih static, bisa di develop
    List<Integer> productIds;
}
