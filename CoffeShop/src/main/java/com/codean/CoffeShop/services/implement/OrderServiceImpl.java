package com.codean.CoffeShop.services.implement;

import com.codean.CoffeShop.exception.NonExistenceResourceException;
import com.codean.CoffeShop.exception.ResourceAlreadyExistException;
import com.codean.CoffeShop.exception.ResourceNotExistException;
import com.codean.CoffeShop.models.dtos.OrderAddDTO;
import com.codean.CoffeShop.models.dtos.OrderGetDTO;
import com.codean.CoffeShop.models.dtos.ProductAddDTO;
import com.codean.CoffeShop.models.dtos.ProductGetDTO;
import com.codean.CoffeShop.models.dtos.responseDTO.OrderResponseDTO;
import com.codean.CoffeShop.models.entities.Order;
import com.codean.CoffeShop.models.entities.Product;
import com.codean.CoffeShop.repositories.OrderRepository;
import com.codean.CoffeShop.services.OrderService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ModelMapper modelMapper;

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    public OrderGetDTO addOrder(OrderAddDTO orderAddDTO) throws ResourceAlreadyExistException {

        Order order1 = new Order();
        order1.setOrderDate(orderAddDTO.getOrderDate());
        order1.setTotalAmount(orderAddDTO.getTotalAmount());

        orderRepository.save(order1);

        return convertToOrderGetDTO(order1);
    }

    @Override
    public OrderResponseDTO getAllOrder(int page_number, int page_size, String sort_order, String sort_by){
        //sorting data
        Sort sortByAndOrders = sort_order.equalsIgnoreCase("ASC")? Sort.by(sort_by).ascending() :
                Sort.by(sort_by).descending();
        //create request
        Pageable detail = PageRequest.of(page_number, page_size, sortByAndOrders);
        //get data from repo
        Page<Order> orderPage = orderRepository.findAll(detail);
        //data to list
        List<Order> orderList = orderPage.getContent();
        //convert DTO
        List<OrderGetDTO> orderGetDTOList = orderList.stream().map(u -> modelMapper.map(u, OrderGetDTO.class)
        ).collect(Collectors.toList());
        //response
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
        orderResponseDTO.setContent(orderGetDTOList);
        orderResponseDTO.setPage_number(orderPage.getNumber());
        orderResponseDTO.setPage_size(orderPage.getSize());
        orderResponseDTO.setTotal_element(orderPage.getTotalElements());
        orderResponseDTO.setLast_page(orderPage.isLast());

        return orderResponseDTO;
    }

    @Override
    public OrderGetDTO getOrderById(UUID order_id) throws ResourceNotExistException {
        Order existedOrder = getExistence(order_id);
        return convertToOrderGetDTO(existedOrder);
    }

    @Override
    public void updateOrderById(UUID order_id, OrderAddDTO orderAddDTO) throws ResourceNotExistException, ResourceAlreadyExistException{

        Order existedOrder = getExistence(order_id);

        Order convertedOrder = convertFromDTO(orderAddDTO);
        checkDuplicate(order_id);

        existedOrder.setOrderDate(orderAddDTO.getOrderDate());
        existedOrder.setTotalAmount(orderAddDTO.getTotalAmount());

        orderRepository.save(existedOrder);
    }

    @Override
    public void deleteOrderById(UUID order_id) throws ResourceNotExistException{

        Order existedOrder = getExistence(order_id);
        orderRepository.delete(existedOrder);

    }

    private void checkDuplicate(UUID orderId) throws ResourceAlreadyExistException{
        Order existedOrder = orderRepository.findOrderByUUID(orderId);
        if (existedOrder != null){
            String errorMsg = "Resource order with same id already exist!";
            logger.error(errorMsg);
            throw new ResourceAlreadyExistException(errorMsg);
        }
    }


    private Order getExistence(UUID orderId) throws ResourceNotExistException {
        Order existedProduct = orderRepository.findOrderByUUID(orderId);
        if (existedProduct == null){
            String errorMsg = "Resource with product id " + orderId + " doesn't exist!";
            logger.error(errorMsg);
            throw new ResourceNotExistException(errorMsg);
        }

        return existedProduct;
    }

    private OrderGetDTO convertToOrderGetDTO(Order order) {
        return modelMapper.map(order, OrderGetDTO.class);
    }
    private OrderAddDTO convertToOrderAddDTO(Order order) {
        return modelMapper.map(order, OrderAddDTO.class);
    }

    private Order convertFromDTO(OrderAddDTO dto){
        return modelMapper.map(dto, Order.class);
    }

    private Order convertFromDTO(OrderGetDTO dto){
        return modelMapper.map(dto, Order.class);
    }

}
