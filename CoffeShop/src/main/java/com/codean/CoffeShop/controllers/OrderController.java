package com.codean.CoffeShop.controllers;

import com.codean.CoffeShop.exception.NonExistenceResourceException;
import com.codean.CoffeShop.exception.ResourceAlreadyExistException;
import com.codean.CoffeShop.exception.ResourceNotExistException;
import com.codean.CoffeShop.models.dtos.OrderAddDTO;
import com.codean.CoffeShop.models.dtos.OrderGetDTO;
import com.codean.CoffeShop.models.dtos.responseDTO.OrderResponseDTO;
import com.codean.CoffeShop.models.entities.Order;
import com.codean.CoffeShop.models.entities.OrderItem;
import com.codean.CoffeShop.models.responses.ApiResponses;
import com.codean.CoffeShop.models.responses.ResponseMessages;
import com.codean.CoffeShop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/add")
    public ApiResponses<Object> addOrder(@Valid @RequestBody OrderAddDTO orderAddDTO) throws ResourceAlreadyExistException{

        OrderGetDTO order = orderService.addOrder(orderAddDTO);

        return ApiResponses.success(HttpStatus.OK, ResponseMessages.RESOURCE_CREATED.getMessages(), null);
    }

    @GetMapping("/getallorderpagination")
    public ResponseEntity<OrderResponseDTO> getOrderByPagination(
            @RequestParam int page_number,
            @RequestParam int page_size,
            @RequestParam String sort_order,
            @RequestParam(defaultValue = "order_id") String sort_by
    ){

        OrderResponseDTO orderResponseDTO = orderService.getAllOrder(page_number, page_size, sort_order, sort_by);

        return new ResponseEntity<OrderResponseDTO>(orderResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/getbyid/{order_id}")
    public ApiResponses<Object> getOrderById(@PathVariable UUID order_id) throws ResourceNotExistException {

        OrderGetDTO order = orderService.getOrderById(order_id);

        return ApiResponses.success(HttpStatus.OK, ResponseMessages.RESOURCE_FETCHED.getMessages(), order);
    }


    @PutMapping("/updatebyid/{order_id}")
    public ApiResponses<Object> updateOrderById(@PathVariable UUID order_id, @RequestBody OrderAddDTO orderAddDTO) throws
            ResourceNotExistException, ResourceAlreadyExistException{

        orderService.updateOrderById(order_id, orderAddDTO);

        return ApiResponses.success(HttpStatus.ACCEPTED, ResponseMessages.RESOURCE_MODIFIED.getMessages(), null);
    }

    @DeleteMapping("/deletebyid/{order_id}")
    public ApiResponses<Object> deleteOrder(@PathVariable UUID order_id) throws ResourceNotExistException{

        orderService.deleteOrderById(order_id);

        return ApiResponses.success(HttpStatus.OK, ResponseMessages.RESOURCE_DELETED.getMessages(), null);
    }

}
