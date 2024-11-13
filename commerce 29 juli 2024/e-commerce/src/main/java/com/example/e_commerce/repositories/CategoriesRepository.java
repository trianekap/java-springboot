package com.example.e_commerce.repositories;

import com.example.e_commerce.model.entities.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, UUID> {
    @Query(value = "SELECT * FROM categories WHERE category_id = ?1", nativeQuery = true)
    Categories findCategoriesById(UUID category_id);

    public Categories findByCategoryName(String categoryName);
}
