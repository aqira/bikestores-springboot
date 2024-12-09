package com.tutorial.bikestores.sales.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query("""
            SELECT c
            FROM Customer c
            JOIN c.user u
            WHERE u.username = :username
            """)
    Optional<Customer> findByUsername(String username);
}
