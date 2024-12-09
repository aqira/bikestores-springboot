package com.tutorial.bikestores.production.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailsDTO{
    private Integer id;
    private String name;
    private Integer modelYear;
    private BigDecimal listPrice;
    private String brandName;
    private String categoryName;
}
