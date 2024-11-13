package com.example.e_commerce.repositories;

import com.example.e_commerce.model.entities.UsersActivityLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsersLogRepository extends JpaRepository<UsersActivityLogEntity, UUID> {

}
