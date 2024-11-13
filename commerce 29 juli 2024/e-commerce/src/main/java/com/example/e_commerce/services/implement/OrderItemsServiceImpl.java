package com.example.e_commerce.services.implement;

import com.example.e_commerce.exceptions.NonExitenceResourceException;
import com.example.e_commerce.exceptions.ResourceAlreadyExistException;
import com.example.e_commerce.exceptions.ResourceNotExistException;
import com.example.e_commerce.model.dtos.OrderItemsAddDTO;
import com.example.e_commerce.model.dtos.OrderItemsGetDTO;
import com.example.e_commerce.model.dtos.ResponseDTO.OrderItemsResponseDTO;
import com.example.e_commerce.model.entities.OrderItems;
import com.example.e_commerce.repositories.OrderItemsRepository;
import com.example.e_commerce.services.OrderItemsService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderItemsServiceImpl implements OrderItemsService {
    @Autowired
    OrderItemsRepository orderItemsRepository;

    @Autowired
    ModelMapper modelMapper;

    private static final Logger logger = LoggerFactory.getLogger(UsersServiceImpl.class);

    @Override
    public OrderItemsGetDTO addOrderItems(OrderItemsGetDTO orderItemsGetDTO) throws ResourceAlreadyExistException {

        OrderItems orderItems = new OrderItems();
        orderItems.setDiscount(orderItemsGetDTO.getDiscount());
        orderItems.setOrderedProductPrice(orderItemsGetDTO.getOrderedProductPrice());
        orderItems.setQuantity(orderItemsGetDTO.getQuantity());
        orderItems.setOrders(orderItemsGetDTO.getOrders());
        orderItems.setProducts(orderItemsGetDTO.getProducts());

        orderItemsRepository.save(orderItems);

        return convertFromDTO(orderItems);

    }

    @Override
    public OrderItemsGetDTO getOrderItemsById(UUID order_items_id, OrderItemsGetDTO orderItemsGetDTO) throws ResourceNotExistException {

        OrderItems orderItems1 = orderItemsRepository.findOrderItemsById(order_items_id);
        if (orderItems1 == null)
            throw new ResourceNotExistException("order items with that id not exist!");

        return convertFromDTO(orderItems1);
    }

    @Override
    public OrderItemsResponseDTO getAllOrderItemsByPagination(int page_number, int page_size, String sort_order, String sort_by){
        //sorting data
        Sort sortByAndOrders = sort_order.equalsIgnoreCase("ASC")? Sort.by(sort_by).ascending() :
                Sort.by(sort_by).descending();
        //create request
        Pageable detail = PageRequest.of(page_number, page_size, sortByAndOrders);
        //get data from repo
        Page<OrderItems> orderItemsPage = orderItemsRepository.findAll(detail);
        //data to list
        List<OrderItems> orderItemsList = orderItemsPage.getContent();
        //convert DTO
        List<OrderItemsAddDTO> orderItemsAddDTOList = orderItemsList.stream().map(u -> modelMapper.map(u, OrderItemsAddDTO.class)
        ).collect(Collectors.toList());
        //response
        OrderItemsResponseDTO orderItemsResponseDTO = new OrderItemsResponseDTO();
        orderItemsResponseDTO.setContent(orderItemsAddDTOList);
        orderItemsResponseDTO.setPage_number(orderItemsPage.getNumber());
        orderItemsResponseDTO.setPage_size(orderItemsPage.getSize());
        orderItemsResponseDTO.setTotal_element(orderItemsPage.getTotalElements());
        orderItemsResponseDTO.setTotal_page(orderItemsPage.getTotalPages());
        orderItemsResponseDTO.setLast_page(orderItemsPage.isLast());

        return orderItemsResponseDTO;
    }

    @Override
    public void updateOrderItemsById(UUID order_items_id, OrderItemsGetDTO orderItemsGetDTO) throws ResourceNotExistException{

        OrderItems orderItems1 = orderItemsRepository.findOrderItemsById(order_items_id);
        if (order_items_id == null)
            throw new ResourceNotExistException("order items id not exist");

        orderItems1.setDiscount(orderItemsGetDTO.getDiscount());
        orderItems1.setOrderedProductPrice(orderItemsGetDTO.getOrderedProductPrice());
        orderItems1.setQuantity(orderItemsGetDTO.getQuantity());

        orderItemsRepository.save(orderItems1);
    }

    @Override
    public void deleteOrderItemsById(UUID order_items_id) throws ResourceNotExistException{

        OrderItems orderItems = orderItemsRepository.findOrderItemsById(order_items_id);
        if (orderItems == null)
            throw new ResourceNotExistException("order items id not exist!");

        orderItemsRepository.delete(orderItems);

    }

    private OrderItemsAddDTO convertedFromDTO(OrderItems orderItems){
        return modelMapper.map(orderItems, OrderItemsAddDTO.class);
    }

    private OrderItemsGetDTO convertFromDTO(OrderItems orderItems){
        return modelMapper.map(orderItems, OrderItemsGetDTO.class);
    }

    private OrderItems convertToOrderItemsAddDTO(OrderItemsAddDTO orderItemsAddDTO){
        return modelMapper.map(orderItemsAddDTO, OrderItems.class);
    }

    private OrderItems convertToOrderItemsGetDTO(OrderItemsGetDTO orderItemsGetDTO){
        return modelMapper.map(orderItemsGetDTO, OrderItems.class);
    }
}
