package com.example.e_commerce.repositories;

import com.example.e_commerce.model.entities.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItems, UUID> {
    @Query(value = "SELECT * FROM cart_items WHERE cart_items_id = ?1", nativeQuery = true)
    CartItems findCartItemsById(UUID cart_items_id);
}
