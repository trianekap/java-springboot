package com.example.e_commerce.repositories;

import com.example.e_commerce.model.entities.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductsRepository extends JpaRepository<Products, UUID> {
    @Query(value = "SELECT * FROM products WHERE product_id = ?1", nativeQuery = true)
    Products findProductsById(UUID product_id);

    @Query(value = "SELECT * FROM products WHERE product_name = ?1", nativeQuery = true)
    Products findProductsByName(String productName);

    @Query(value = "SELECT * FROM products p WHERE p.product_name LIKE '%' || :keyword || '%' \n" +
            "OR p.description LIKE '%' || :keyword || '%'", nativeQuery = true)
    List<Products> findProductsByKeyword(@Param("keyword") String keyword);

}
