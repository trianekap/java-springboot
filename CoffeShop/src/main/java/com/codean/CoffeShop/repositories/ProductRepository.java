package com.codean.CoffeShop.repositories;

import com.codean.CoffeShop.models.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query("SELECT p FROM Product p WHERE " +
            "p.name LIKE CONCAT ('%',:query,'%')" +
            "Or p.description LIKE CONCAT('%',:query,'%')")
    List<Product> searchProduct(String query);

//    Page<Product> findProductNamePagination(String name, PageRequest pageRequest);

    @Query(value = "SELECT * FROM product WHERE product_id = ?1", nativeQuery = true)
    Product findProductByUUID(UUID product_id);

    Product findProductByName(String productName);

//    Page<Product> findProductByPrice(UUID productId);
//
//    Page<Product> findProductByUUIDPage(UUID productId, Pageable pageable);


}
