package com.codean.CoffeShop.controllers;

import com.codean.CoffeShop.exception.ResourceAlreadyExistException;
import com.codean.CoffeShop.exception.ResourceNotExistException;
import com.codean.CoffeShop.models.dtos.OrderItemAddDTO;
import com.codean.CoffeShop.models.dtos.OrderItemGetDTO;
import com.codean.CoffeShop.models.dtos.responseDTO.OrderItemResponseDTO;
import com.codean.CoffeShop.models.entities.OrderItem;
import com.codean.CoffeShop.models.responses.ApiResponses;
import com.codean.CoffeShop.models.responses.ResponseMessages;
import com.codean.CoffeShop.services.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/orderitem")
public class OrderItemController {

    @Autowired
    OrderItemService orderItemService;

    @PostMapping("/add")
    public ApiResponses<Object> addOrderItem(@Valid @RequestBody OrderItemAddDTO orderItemAddDTO) throws ResourceAlreadyExistException, ResourceNotExistException{

        OrderItemGetDTO orderItem = orderItemService.addOrderItem(orderItemAddDTO);

        return ApiResponses.success(HttpStatus.OK, ResponseMessages.RESOURCE_CREATED.getMessages(), null);
    }


    @GetMapping("/getallorderitempagination")
    public ResponseEntity<OrderItemResponseDTO> getOrderByPagination(
            @RequestParam int page_number,
            @RequestParam int page_size,
            @RequestParam String sort_order,
            @RequestParam(defaultValue = "productName") String sort_by
    ){

        OrderItemResponseDTO orderItemResponseDTO = orderItemService.getAllOrderItem(page_number, page_size, sort_order, sort_by);

        return new ResponseEntity<OrderItemResponseDTO>(orderItemResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/getbyid/{order_item_id}")
    public ApiResponses<Object> getOrderItemById(@PathVariable UUID order_item_id) throws ResourceNotExistException {

        OrderItemGetDTO orderItem = orderItemService.getOrderItemById(order_item_id);

        return ApiResponses.success(HttpStatus.OK, ResponseMessages.RESOURCE_FETCHED.getMessages(), orderItem);
    }


    @PutMapping("/updatebyid/{order_item_id}")
    public ApiResponses<Object> updateOrderItemById(@PathVariable UUID order_item_id, @RequestBody OrderItemAddDTO orderItemAddDTO) throws
            ResourceAlreadyExistException, ResourceNotExistException{

        orderItemService.updateOrderItemById(order_item_id, orderItemAddDTO);

        return ApiResponses.success(HttpStatus.ACCEPTED, ResponseMessages.RESOURCE_MODIFIED.getMessages(), null);
    }

    @DeleteMapping("/deletebyid/{order_item_id}")
    public ApiResponses<OrderItem> deleteOrderItem(@PathVariable UUID order_item_id) throws ResourceNotExistException{

        orderItemService.deleteOrderItemById(order_item_id);

        return ApiResponses.success(HttpStatus.OK, ResponseMessages.RESOURCE_DELETED.getMessages(), null);
    }
}
