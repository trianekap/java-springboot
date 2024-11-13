package com.example.e_commerce.services;

import com.example.e_commerce.exceptions.ResourceAlreadyExistException;
import com.example.e_commerce.exceptions.ResourceNotExistException;
import com.example.e_commerce.model.dtos.ResponseDTO.CartsResponseDTO;
import com.example.e_commerce.model.dtos.CartsAddDTO;
import com.example.e_commerce.model.dtos.CartsGetDTO;

import java.util.UUID;

public interface CartsService {
    CartsGetDTO addCarts(CartsAddDTO cartsAddDTO) throws ResourceAlreadyExistException;

    CartsResponseDTO getAllCartsByPagination(int page_number, int page_size, String sort_order, String sort_by);

    CartsGetDTO getCartsById(UUID cart_id, CartsGetDTO cartsGetDTO) throws ResourceNotExistException;

    void updateCartsById(UUID cart_id, CartsAddDTO cartsAddDTO) throws ResourceNotExistException;

    void deleteCartsById(UUID cart_id) throws ResourceNotExistException;
}
