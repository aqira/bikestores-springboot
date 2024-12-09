package com.tutorial.bikestores.production.product;

import com.tutorial.bikestores.production.brand.Brand;
import com.tutorial.bikestores.production.category.Category;
import com.tutorial.bikestores.production.stock.Stock;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(schema = "production", name = "products")
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "model_year")
    private Integer modelYear;

    @Column(name = "list_price")
    private BigDecimal listPrice;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToOne(mappedBy = "product")
    private Stock stock;

    public Product(Integer id) {
        this.id = id;
    }
}