package com.example.e_commerce.repositories;

import com.example.e_commerce.model.entities.CartsActivityLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CartsLogRepository extends JpaRepository<CartsActivityLogEntity, UUID> {
}
