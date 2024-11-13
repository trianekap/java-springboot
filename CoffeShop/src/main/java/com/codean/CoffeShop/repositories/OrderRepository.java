package com.codean.CoffeShop.repositories;

import com.codean.CoffeShop.models.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    @Query(value = "SELECT * FROM orders WHERE order_id = ?1", nativeQuery = true)
    public Order findOrderByUUID(UUID order_id);

    public Order findByOrderDate(Date orderDate);

}
