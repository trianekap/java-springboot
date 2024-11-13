package com.example.e_commerce.repositories;

import com.example.e_commerce.model.entities.OrderItems;
import com.example.e_commerce.model.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, UUID> {
    @Query(value = "SELECT * FROM orders WHERE order_id = ?1", nativeQuery = true)
    Orders findOrdersById(UUID order_id);
}
