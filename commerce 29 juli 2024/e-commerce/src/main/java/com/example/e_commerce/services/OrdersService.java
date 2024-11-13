package com.example.e_commerce.services;

import com.example.e_commerce.exceptions.ResourceAlreadyExistException;
import com.example.e_commerce.exceptions.ResourceNotExistException;
import com.example.e_commerce.model.dtos.OrdersAddDTO;
import com.example.e_commerce.model.dtos.OrdersGetDTO;
import com.example.e_commerce.model.dtos.ResponseDTO.OrdersResponseDTO;

import java.util.UUID;

public interface OrdersService {
    OrdersGetDTO addOrders(OrdersGetDTO ordersGetDTO) throws ResourceAlreadyExistException;

    OrdersResponseDTO getAllOrdersByPagination(int page_number, int page_size, String sort_order, String sort_by);

    OrdersGetDTO getOrdersById(UUID orders_id, OrdersGetDTO ordersGetDTO) throws ResourceNotExistException;

    void updateOrdersById(UUID orders_id, OrdersAddDTO ordersAddDTO) throws ResourceNotExistException;

    void deleteOrdersById(UUID orders_id) throws ResourceNotExistException;
}
