package com.tutorial.bikestores.production.stock;

import com.tutorial.bikestores.production.product.Product;
import com.tutorial.bikestores.sales.customer.Customer;
import com.tutorial.bikestores.sales.store.Store;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(schema = "production", name = "stocks")
public class Stock {

    @EmbeddedId
    private StockId id;

    @Column(name = "quantity")
    private Integer quantity;

    @OneToOne
    @MapsId("storeId")
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToMany(mappedBy = "cart")
    private List<Customer> interestedCustomers;

    public Stock(StockId stockId) {
        this.id = stockId;
    }
}
