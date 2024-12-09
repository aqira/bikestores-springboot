package com.tutorial.bikestores.clients.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductCatalogueDTO {
    private final Integer id;
    private final String name;
    private final String brandName;
    private final String categoryName;
    private final Integer modelYear;
    private final BigDecimal listPrice;
}
