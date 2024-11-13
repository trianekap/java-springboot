package com.example.e_commerce.services;

import com.example.e_commerce.exceptions.ResourceAlreadyExistException;
import com.example.e_commerce.exceptions.ResourceNotExistException;
import com.example.e_commerce.model.dtos.ProductsAddDTO;
import com.example.e_commerce.model.dtos.ProductsGetDTO;
import com.example.e_commerce.model.dtos.ResponseDTO.ProductsResponseDTO;
import com.example.e_commerce.model.entities.Products;

import java.util.List;
import java.util.UUID;

public interface ProductsService {
    ProductsGetDTO addProducts(ProductsAddDTO productsAddDTO) throws ResourceAlreadyExistException;

    ProductsResponseDTO getAllProductsPagination(int page_number, int page_size, String sort_order, String sort_by);

    ProductsGetDTO getProductsById(UUID product_id, ProductsGetDTO productsGetDTO) throws ResourceNotExistException;

    void updateProductsById(UUID product_id, ProductsAddDTO productsAddDTO) throws ResourceAlreadyExistException, ResourceNotExistException;

    void deleteProductsById(UUID product_id) throws ResourceNotExistException;

    List<ProductsGetDTO> findProductsByKeyword(String keyword) throws ResourceNotExistException;
}
