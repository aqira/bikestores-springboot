package com.tutorial.bikestores.production.brand;

import com.tutorial.bikestores.production.product.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(schema = "production", name = "brands")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private Integer id;
    @Column(name = "brand_name")
    private String name;
    @OneToMany(mappedBy = "brand")
    private Set<Product> products;

    public Brand(Integer id) {
        this.id = id;
    }
}
