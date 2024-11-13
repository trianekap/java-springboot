package com.example.e_commerce.controllers;

import com.example.e_commerce.exceptions.ResourceAlreadyExistException;
import com.example.e_commerce.exceptions.ResourceNotExistException;
import com.example.e_commerce.model.dtos.OrdersAddDTO;
import com.example.e_commerce.model.dtos.OrdersGetDTO;
import com.example.e_commerce.model.dtos.ResponseDTO.OrdersResponseDTO;
import com.example.e_commerce.model.responses.ApiResponses;
import com.example.e_commerce.model.responses.ResponseMessages;
import com.example.e_commerce.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/")
public class OrdersController {

    @Autowired
    OrdersService ordersService;

    @PostMapping("/addorder")
    public ApiResponses<Object> addOrders(@RequestBody OrdersGetDTO ordersGetDTO) throws ResourceAlreadyExistException{

        OrdersGetDTO ordersGetDTO1 = ordersService.addOrders(ordersGetDTO);
        return ApiResponses.success(HttpStatus.OK, ResponseMessages.RESOURCE_CREATED.getMessages(), null);
    }

    @GetMapping("/getallorderspagination")
    public ResponseEntity<OrdersResponseDTO> getOrdersByPagination(
            @RequestParam int page_number,
            @RequestParam int page_size,
            @RequestParam String sort_order,
            @RequestParam(defaultValue = "id") String sort_by
    ){

        OrdersResponseDTO ordersResponseDTO = ordersService.getAllOrdersByPagination(page_number, page_size, sort_order, sort_by);
        return new ResponseEntity<OrdersResponseDTO>(ordersResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/getordersbyid/{order_id}")
    public ApiResponses<Object> getOrdersById(@PathVariable UUID order_id, OrdersGetDTO ordersGetDTO) throws ResourceNotExistException {

        OrdersGetDTO orders1 = ordersService.getOrdersById(order_id, ordersGetDTO);
        return ApiResponses.success(HttpStatus.OK, ResponseMessages.RESOURCE_FETCHED.getMessages(), orders1);
    }

    @PutMapping("/updateordersbyid/{order_id}")
    public ApiResponses<Object> updateOrdersById(@PathVariable UUID order_id, @RequestBody OrdersAddDTO ordersAddDTO) throws ResourceNotExistException{

        ordersService.updateOrdersById(order_id, ordersAddDTO);
        return ApiResponses.success(HttpStatus.OK, ResponseMessages.RESOURCE_MODIFIED.getMessages(), null);
    }

    @DeleteMapping("/deleteorders/{order_id}")
    public ApiResponses<Object> deleteOrdersById(@PathVariable UUID order_id) throws ResourceNotExistException{
        ordersService.deleteOrdersById(order_id);
        return ApiResponses.success(HttpStatus.OK, ResponseMessages.RESOURCE_DELETED.getMessages(), null);
    }
}
