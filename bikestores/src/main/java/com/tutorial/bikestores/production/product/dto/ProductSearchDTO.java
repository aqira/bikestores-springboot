package com.tutorial.bikestores.production.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ProductSearchDTO {
    private Integer pageNumber = 1;
    private String name;
    private Integer brandId;
    private Integer categoryId;
    private Integer modelYear;
    private BigDecimal listPrice;
}
