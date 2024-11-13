package com.example.e_commerce.services;

import com.example.e_commerce.exceptions.NonExitenceResourceException;
import com.example.e_commerce.exceptions.ResourceAlreadyExistException;
import com.example.e_commerce.exceptions.ResourceNotExistException;
import com.example.e_commerce.model.dtos.OrderItemsAddDTO;
import com.example.e_commerce.model.dtos.OrderItemsGetDTO;
import com.example.e_commerce.model.dtos.ResponseDTO.OrderItemsResponseDTO;
import com.example.e_commerce.model.entities.OrderItems;

import java.util.UUID;

public interface OrderItemsService {
    OrderItemsGetDTO addOrderItems(OrderItemsGetDTO orderItemsGetDTO) throws ResourceAlreadyExistException;

    OrderItemsResponseDTO getAllOrderItemsByPagination(int page_number, int page_size, String sort_order, String sort_by);

    OrderItemsGetDTO getOrderItemsById(UUID order_items_id, OrderItemsGetDTO orderItemsGetDTO) throws ResourceNotExistException;

    void updateOrderItemsById(UUID order_items_id, OrderItemsGetDTO orderItemsGetDTO) throws ResourceNotExistException;

    void deleteOrderItemsById(UUID order_items_id) throws ResourceNotExistException;
}
