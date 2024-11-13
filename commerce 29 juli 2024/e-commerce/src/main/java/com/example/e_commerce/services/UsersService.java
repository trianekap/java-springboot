package com.example.e_commerce.services;


import com.example.e_commerce.exceptions.ResourceInvalidConstraintViolation;
import com.example.e_commerce.exceptions.ResourceNotExistException;
import com.example.e_commerce.model.dtos.UsersAddDTO;
import com.example.e_commerce.model.dtos.UsersGetDTO;
import com.example.e_commerce.model.dtos.ResponseDTO.UsersResponseDTO;
import com.example.e_commerce.exceptions.ResourceAlreadyExistException;

import java.util.List;
import java.util.UUID;

public interface UsersService {
    UsersGetDTO addUsers(UsersAddDTO usersAddDto) throws ResourceAlreadyExistException, ResourceNotExistException;

    UsersResponseDTO getAllUsers(int page_number, int page_size, String sort_order, String sort_by);

    UsersGetDTO getUsersById(UUID user_id, UsersGetDTO usersGetDTO) throws ResourceNotExistException;

    void updateUsersById(UUID user_id, UsersGetDTO usersGetDTO) throws ResourceNotExistException, ResourceAlreadyExistException, ResourceInvalidConstraintViolation;

    void deleteUsersById(UUID user_id) throws ResourceNotExistException;

    List<UsersGetDTO> getUserByKeyword(String keyword) throws ResourceNotExistException;


}
