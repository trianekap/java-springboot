package com.codean.CoffeShop.services;


import com.codean.CoffeShop.exception.NonExistenceResourceException;
import com.codean.CoffeShop.exception.ResourceAlreadyExistException;
import com.codean.CoffeShop.exception.ResourceNotExistException;
import com.codean.CoffeShop.models.dtos.OrderItemAddDTO;
import com.codean.CoffeShop.models.dtos.OrderItemGetDTO;
import com.codean.CoffeShop.models.dtos.responseDTO.OrderItemResponseDTO;

import java.util.UUID;

public interface OrderItemService {
    OrderItemGetDTO addOrderItem(OrderItemAddDTO orderItemAddDTO) throws ResourceAlreadyExistException, ResourceNotExistException;

    OrderItemResponseDTO getAllOrderItem(int page_number, int page_size, String sort_order, String sort_by);

    OrderItemGetDTO getOrderItemById(UUID order_item_id) throws ResourceNotExistException;

    void updateOrderItemById(UUID order_item_id, OrderItemAddDTO orderItemAddDTO) throws ResourceNotExistException, ResourceAlreadyExistException;

    void deleteOrderItemById(UUID order_item_id) throws ResourceNotExistException;
}
