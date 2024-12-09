package com.tutorial.bikestores.sales.customer;

import com.tutorial.bikestores.production.stock.Stock;
import com.tutorial.bikestores.security.user.User;
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
@Table(schema = "sales", name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "street")
    private String street;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name = "zip_code")
    private String zipCode;

    @OneToOne
    @JoinColumn(name = "username")
    private User user;

    @ManyToMany
    @JoinTable(
            schema = "sales", name = "carts",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = {
                    @JoinColumn(name = "product_id"),
                    @JoinColumn(name = "store_id")
            }
    )
    private List<Stock> cart;

}
