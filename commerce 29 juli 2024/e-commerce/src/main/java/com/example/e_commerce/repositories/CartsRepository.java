package com.example.e_commerce.repositories;

import com.example.e_commerce.model.entities.Carts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CartsRepository extends JpaRepository<Carts, UUID> {

    @Query(value = "SELECT * FROM carts WHERE cart_id = ?1", nativeQuery = true)
    Carts findCartsById(UUID cart_id);

}
