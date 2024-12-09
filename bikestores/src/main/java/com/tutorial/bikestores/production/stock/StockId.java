package com.tutorial.bikestores.production.stock;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class StockId {

    @Column(name = "product_id")
    private Integer productId;
    @Column(name = "store_id")
    private Integer storeId;
}
