package com.tutorial.bikestores.clients.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductCatalogueSearchDTO {
    private Integer pageNumber = 1;
    private String name;
    private Integer brandId;
    private Integer categoryId;
    private Integer modelYear;
    private BigDecimal listPrice;
}
