package com.example.e_commerce.repositories;

import com.example.e_commerce.model.entities.CartItemsActivityLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CartItemsLogRepository extends JpaRepository<CartItemsActivityLogEntity, UUID> {
}
