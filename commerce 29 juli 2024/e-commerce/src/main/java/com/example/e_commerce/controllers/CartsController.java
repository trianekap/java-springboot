package com.example.e_commerce.controllers;

import com.example.e_commerce.exceptions.NonExitenceResourceException;
import com.example.e_commerce.exceptions.ResourceAlreadyExistException;
import com.example.e_commerce.exceptions.ResourceNotExistException;
import com.example.e_commerce.model.dtos.ResponseDTO.CartsResponseDTO;
import com.example.e_commerce.model.dtos.CartsAddDTO;
import com.example.e_commerce.model.dtos.CartsGetDTO;
import com.example.e_commerce.model.entities.Carts;
import com.example.e_commerce.model.responses.ApiResponses;
import com.example.e_commerce.model.responses.ResponseMessages;
import com.example.e_commerce.services.CartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class CartsController {
    @Autowired
    CartsService cartsService;

    @PostMapping("/addcart")
    public ApiResponses<Objects> addCart(@Valid @RequestBody CartsAddDTO cartsAddDTO) throws ResourceAlreadyExistException{

        CartsGetDTO cartsGetDTO = cartsService.addCarts(cartsAddDTO);

        return ApiResponses.success(HttpStatus.OK, ResponseMessages.RESOURCE_CREATED.getMessages(), null);
    }

    @GetMapping("/getallcartspagination")
    public ResponseEntity<CartsResponseDTO> getCartsByPagination(
            @RequestParam int page_number,
            @RequestParam int page_size,
            @RequestParam String sort_order,
            @RequestParam(defaultValue = "id") String sort_by
    ){

        CartsResponseDTO cartResponseDTO = cartsService.getAllCartsByPagination(page_number, page_size, sort_order, sort_by);

        return new ResponseEntity<CartsResponseDTO>(cartResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/getcartbyid/{cart_id}")
    public ApiResponses<Object> getCartsById(@PathVariable UUID cart_id, CartsGetDTO cartsGetDTO) throws ResourceNotExistException {

        CartsGetDTO carts = cartsService.getCartsById(cart_id, cartsGetDTO);

        return ApiResponses.success(HttpStatus.OK, ResponseMessages.RESOURCE_FETCHED.getMessages(), carts);
    }


    @PutMapping("/updatecartbyid/{cart_id}")
    public ApiResponses<Object> updateCartsById(@PathVariable UUID cart_id, @RequestBody CartsAddDTO cartsAddDTO) throws ResourceNotExistException, ResourceAlreadyExistException{

        cartsService.updateCartsById(cart_id, cartsAddDTO);


        return ApiResponses.success(HttpStatus.ACCEPTED, ResponseMessages.RESOURCE_MODIFIED.getMessages(), null);
    }

    @DeleteMapping("/deletecart/{cart_id}")
    public ApiResponses<Object> deleteCartsById(@PathVariable UUID cart_id) throws ResourceNotExistException{

        cartsService.deleteCartsById(cart_id);

        return ApiResponses.success(HttpStatus.OK, ResponseMessages.RESOURCE_DELETED.getMessages(), null);
    }
}
