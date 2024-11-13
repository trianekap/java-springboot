package com.example.e_commerce.services;

import com.example.e_commerce.exceptions.NonExitenceResourceException;
import com.example.e_commerce.exceptions.ResourceAlreadyExistException;
import com.example.e_commerce.exceptions.ResourceNotExistException;
import com.example.e_commerce.model.dtos.CategoriesDTO;
import com.example.e_commerce.model.dtos.ResponseDTO.CategoriesResponseDTO;
import com.example.e_commerce.model.entities.Categories;

import java.util.List;
import java.util.UUID;

public interface CategoriesService {
    CategoriesDTO addCategories(CategoriesDTO categoriesDTO) throws ResourceAlreadyExistException;

    CategoriesResponseDTO getAllCategoriesByPagination(int page_number, int page_size, String sort_order, String sort_by);

    CategoriesDTO getCategoriesById(UUID categories_id, CategoriesDTO categoriesDTO) throws ResourceNotExistException;

    void updateCategoriesById(UUID categories_id, CategoriesDTO categoriesDTO) throws ResourceNotExistException, ResourceAlreadyExistException;

    void deleteCategoriesById(UUID categories_id) throws ResourceNotExistException;
}
