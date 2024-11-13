package com.example.e_commerce.repositories;

import com.example.e_commerce.model.entities.Categories;
import com.example.e_commerce.model.entities.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems, UUID> {
    @Query(value = "SELECT * FROM order_items WHERE order_item_id = ?1", nativeQuery = true)
    OrderItems findOrderItemsById(UUID order_item_id);
}
