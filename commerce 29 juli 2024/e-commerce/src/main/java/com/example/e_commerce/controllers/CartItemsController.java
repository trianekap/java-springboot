package com.example.e_commerce.controllers;

import com.example.e_commerce.exceptions.NonExitenceResourceException;
import com.example.e_commerce.exceptions.ResourceAlreadyExistException;
import com.example.e_commerce.exceptions.ResourceNotExistException;
import com.example.e_commerce.model.dtos.CartItemsAddDTO;
import com.example.e_commerce.model.dtos.CartItemsGetDTO;
import com.example.e_commerce.model.dtos.ResponseDTO.CartItemsResponseDTO;
import com.example.e_commerce.model.entities.CartItems;
import com.example.e_commerce.model.responses.ApiResponses;
import com.example.e_commerce.model.responses.ResponseMessages;
import com.example.e_commerce.services.CartItemsService;
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
public class CartItemsController {
    @Autowired
    CartItemsService cartItemsService;

    @PostMapping("/addcartitem")
    public ApiResponses<Objects> addCartItems(@Valid @RequestBody CartItemsAddDTO cartItemsAddDTO) throws ResourceAlreadyExistException{

        CartItemsGetDTO cartItems = cartItemsService.addCartItems(cartItemsAddDTO);

        return ApiResponses.success(HttpStatus.OK, ResponseMessages.RESOURCE_CREATED.getMessages(), null);
    }

    @GetMapping("/getallcartitemspagination")
    public ResponseEntity<CartItemsResponseDTO> getUsersByPagination(
            @RequestParam int page_number,
            @RequestParam int page_size,
            @RequestParam String sort_order,
            @RequestParam(defaultValue = "mobilenumber") String sort_by
    ){

        CartItemsResponseDTO cartItemsResponseDTO = cartItemsService.getAllCartItemsPagination(page_number, page_size, sort_order, sort_by);

        return new ResponseEntity<CartItemsResponseDTO>(cartItemsResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/getcartitemsbyid/{cart_items_id}")
    public ApiResponses<CartItemsGetDTO> getCartItemsById(@PathVariable UUID cart_items_id) throws ResourceNotExistException {

        CartItemsGetDTO cartItems = cartItemsService.getCartItemsById(cart_items_id);

        return ApiResponses.success(HttpStatus.OK, ResponseMessages.RESOURCE_FETCHED.getMessages(), cartItems);
    }

    @PutMapping("/updatecartitemsbyid/{cart_items_id}")
    public ApiResponses<Object> updateCartItemsById(@PathVariable UUID cart_items_id, @RequestBody CartItemsAddDTO cartItemsAddDTO) throws ResourceAlreadyExistException,
            ResourceNotExistException {

        cartItemsService.updateCartItemsById(cart_items_id, cartItemsAddDTO);

        return ApiResponses.success(HttpStatus.ACCEPTED, ResponseMessages.RESOURCE_MODIFIED.getMessages(), null);
    }

    @DeleteMapping("/deletecartitems/{cart_items_id}")
    public ApiResponses<CartItems> deleteCartItemsById(@PathVariable UUID cart_items_id) throws ResourceNotExistException{

            cartItemsService.deleteCartItemsById(cart_items_id);

        return ApiResponses.success(HttpStatus.OK, ResponseMessages.RESOURCE_DELETED.getMessages(), null);
    }
}
