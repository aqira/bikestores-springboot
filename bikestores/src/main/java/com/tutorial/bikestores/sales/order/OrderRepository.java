package com.tutorial.bikestores.sales.order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    /**
     * TABEL SALES.ORDERS
     * --customer id ambil dari USERNAME
     * --order status 1 aja
     * -- order date localDate.now()
     * -- required date localDate.now().plusDays(7)
     * -- shipped date null
     * -- store_id ambil dari CART
     * -- staff_id 1
     *
     * TABEL SALES.ORDER_ITEMS
     * --order_id ambil dari orders
     * -- item_id @Generated
     * --product_id (many) ambil dari CART
     * --quantity 1 aja
     * --list price jiplak dari tabel product
     * --discount 0
     *
     * SETELAH UDAH SELESAI BIKIN DI ATAS SEMUA, JANGAN LUPA UNTUK DELETE YANG ADA DI CARTS
     */
}
