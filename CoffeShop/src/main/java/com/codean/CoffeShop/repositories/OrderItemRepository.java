package com.codean.CoffeShop.repositories;

import com.codean.CoffeShop.models.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {

    @Query(value = "SELECT * FROM order_item WHERE order_item_id = ?1", nativeQuery = true)
    public OrderItem findOrderItemByUUID(UUID order_item_id);

    public OrderItem findOrderItemByQuantity(Integer quantity);

}
