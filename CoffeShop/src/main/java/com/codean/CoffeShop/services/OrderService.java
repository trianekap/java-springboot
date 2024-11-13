package com.codean.CoffeShop.services;


import com.codean.CoffeShop.exception.NonExistenceResourceException;
import com.codean.CoffeShop.exception.ResourceAlreadyExistException;
import com.codean.CoffeShop.exception.ResourceNotExistException;
import com.codean.CoffeShop.models.dtos.OrderAddDTO;
import com.codean.CoffeShop.models.dtos.OrderGetDTO;
import com.codean.CoffeShop.models.dtos.responseDTO.OrderResponseDTO;
import com.codean.CoffeShop.models.dtos.responseDTO.ProductResponseDTO;
import com.codean.CoffeShop.models.entities.Order;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


public interface OrderService {
    OrderGetDTO addOrder(OrderAddDTO orderAddDTO) throws ResourceAlreadyExistException;

    OrderResponseDTO getAllOrder(int page_number, int page_size, String sort_order, String sort_by);

    OrderGetDTO getOrderById(UUID order_id) throws ResourceNotExistException;

    void updateOrderById(UUID order_id, OrderAddDTO orderAddDTO) throws ResourceNotExistException, ResourceAlreadyExistException;

    void deleteOrderById(UUID order_id) throws ResourceNotExistException;
}
