package com.example.e_commerce.repositories;

import com.example.e_commerce.model.entities.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PaymentsRepository extends JpaRepository<Payments, UUID> {

    @Query(value = "SELECT * FROM payments WHERE payment_id = ?1", nativeQuery = true)
    Payments findPaymentsById(UUID payment_id);

    @Query(value = "SELECT * FROM payments WHERE payment_method = ?1", nativeQuery = true)
    Payments findPaymentsByName(String payment_method);
}
