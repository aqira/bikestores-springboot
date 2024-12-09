package com.tutorial.bikestores.sales.orderitem;

import com.tutorial.bikestores.production.product.Product;
import com.tutorial.bikestores.sales.order.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(schema = "sales", name = "order_items")
public class OrderItem {
    @EmbeddedId
    private OrderItemId id;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "list_price")
    private BigDecimal listPrice;
    @Column(name = "discount")
    private Double discount;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}