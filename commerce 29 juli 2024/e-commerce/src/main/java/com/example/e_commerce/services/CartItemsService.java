package com.example.e_commerce.services;

import com.example.e_commerce.exceptions.NonExitenceResourceException;
import com.example.e_commerce.exceptions.ResourceAlreadyExistException;
import com.example.e_commerce.exceptions.ResourceNotExistException;
import com.example.e_commerce.model.dtos.CartItemsAddDTO;
import com.example.e_commerce.model.dtos.CartItemsGetDTO;
import com.example.e_commerce.model.dtos.ResponseDTO.CartItemsResponseDTO;
import com.example.e_commerce.model.entities.CartItems;

import java.util.List;
import java.util.UUID;

public interface CartItemsService {
    CartItemsGetDTO addCartItems(CartItemsAddDTO cartItemsAddDTO) throws ResourceAlreadyExistException;

    CartItemsResponseDTO getAllCartItemsPagination(int page_number, int page_size, String sort_order, String sort_by);

    CartItemsGetDTO getCartItemsById(UUID cart_item_id) throws ResourceNotExistException;

    void updateCartItemsById(UUID cart_item_id, CartItemsAddDTO cartItemsAddDTO) throws ResourceNotExistException;

    void deleteCartItemsById(UUID cart_items_id) throws ResourceNotExistException;
}
