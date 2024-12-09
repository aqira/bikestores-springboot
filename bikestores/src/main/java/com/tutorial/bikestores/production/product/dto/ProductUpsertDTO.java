package com.tutorial.bikestores.production.product.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpsertDTO{
    private Integer id;
    @NotBlank
    private String name;
    private Integer modelYear;
    private BigDecimal listPrice;
    private Integer brandId;
    private Integer categoryId;
}
