package com.example.e_commerce.controllers;


import com.example.e_commerce.exceptions.ResourceAlreadyExistException;
import com.example.e_commerce.exceptions.ResourceNotExistException;
import com.example.e_commerce.model.dtos.OrderItemsGetDTO;
import com.example.e_commerce.model.dtos.ResponseDTO.OrderItemsResponseDTO;
import com.example.e_commerce.model.entities.OrderItems;
import com.example.e_commerce.model.responses.ApiResponses;
import com.example.e_commerce.model.responses.ResponseMessages;
import com.example.e_commerce.services.OrderItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/api/v1/")
public class OrderItemsController {

    @Autowired
    OrderItemsService orderItemsService;

    @PostMapping("/addorderitem")
    public ApiResponses<Object> addOrderItems(@RequestBody OrderItemsGetDTO orderItemsGetDTO) throws ResourceAlreadyExistException {

        OrderItemsGetDTO orderItems1 = orderItemsService.addOrderItems(orderItemsGetDTO);

        return ApiResponses.success(HttpStatus.OK, ResponseMessages.RESOURCE_CREATED.getMessages(), null);
    }

    @GetMapping("/getorderitems/{order_items_id}")
    public ApiResponses<Object> getOrderItems(@PathVariable UUID order_items_id, OrderItemsGetDTO orderItemsGetDTO) throws ResourceNotExistException {

        OrderItemsGetDTO orderItems1 = orderItemsService.getOrderItemsById(order_items_id, orderItemsGetDTO);

        return ApiResponses.success(HttpStatus.OK, ResponseMessages.RESOURCE_FETCHED.getMessages(), orderItems1);
    }

    @GetMapping("/getallorderitemspagination")
    public ResponseEntity<OrderItemsResponseDTO> getCategoriesByPagination(
            @RequestParam int page_number,
            @RequestParam int page_size,
            @RequestParam String sort_order,
            @RequestParam(defaultValue = "id") String sort_by
    ){

        OrderItemsResponseDTO orderItemsResponseDTO = orderItemsService.getAllOrderItemsByPagination(page_number, page_size, sort_order, sort_by);

        return new ResponseEntity<OrderItemsResponseDTO>(orderItemsResponseDTO, HttpStatus.OK);
    }

    @PutMapping("/updateorderitemsbyid/{order_items_id}")
    public ApiResponses<OrderItems> updateOrderItemsById(@PathVariable UUID order_items_id, @RequestBody OrderItemsGetDTO orderItemsGetDTO) throws ResourceNotExistException{

        orderItemsService.updateOrderItemsById(order_items_id, orderItemsGetDTO);

        return ApiResponses.success(HttpStatus.OK, ResponseMessages.RESOURCE_MODIFIED.getMessages(), null);
    }

    @DeleteMapping("/deleteitemsbyid/{order_items_id}")
    public ApiResponses<Object> deleteOrderItemsById(@PathVariable UUID order_items_id) throws ResourceNotExistException {

        orderItemsService.deleteOrderItemsById(order_items_id);
        return ApiResponses.success(HttpStatus.OK, ResponseMessages.RESOURCE_DELETED.getMessages(), null);
    }
}
