package com.codean.CoffeShop.services;


import com.codean.CoffeShop.exception.NonExistenceResourceException;
import com.codean.CoffeShop.exception.ResourceAlreadyExistException;
import com.codean.CoffeShop.exception.ResourceNotExistException;
import com.codean.CoffeShop.models.dtos.ProductAddDTO;
import com.codean.CoffeShop.models.dtos.ProductGetDTO;
import com.codean.CoffeShop.models.dtos.responseDTO.ProductResponseDTO;
import com.codean.CoffeShop.models.entities.Product;

import java.util.List;
import java.util.UUID;


public interface ProductService {

    ProductGetDTO addProduct(ProductAddDTO productAddDTO) throws ResourceAlreadyExistException, ResourceNotExistException;

    List<Product> searchProduct(String query);

    ProductResponseDTO getAllProducts(int page_number, int page_size, String sort_order, String sort_by);

    ProductGetDTO getProductById(UUID product_id) throws ResourceNotExistException;

    void updateProductById(UUID product_id, ProductAddDTO productAddDTO) throws ResourceNotExistException, ResourceAlreadyExistException;

    void deleteProductById(UUID product_id) throws ResourceNotExistException;
}
