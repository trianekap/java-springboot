package com.example.e_commerce.repositories;

import com.example.e_commerce.model.dtos.UsersAddDTO;
import com.example.e_commerce.model.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UsersRepository extends JpaRepository<Users, UUID> {

    @Query(value = "SELECT * FROM users WHERE mobile_number = ?1", nativeQuery = true)
    Users findUsersByMobileNumber(String mobile_number);

    @Query(value = "SELECT * FROM users WHERE user_id = ?1", nativeQuery = true)
    Users findUsersById(UUID user_id);

    @Query(value = "SELECT * FROM users WHERE first_name = ?1", nativeQuery = true)
    Users findUsersByName(String firstName);

    @Query(value = "SELECT * FROM users u WHERE u.email LIKE '%' || :keyword || '%' \n" +
            "OR u.mobile_number LIKE '%' || :keyword || '%'\n" +
            "OR u.first_name LIKE '%' || :keyword || '%' " , nativeQuery = true)
    List<Users> findUsersByKeyword(@Param("keyword") String keyword);

}
