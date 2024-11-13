package com.example.e_commerce.services.implement;

import com.example.e_commerce.exceptions.ResourceAlreadyExistException;
import com.example.e_commerce.exceptions.ResourceNotExistException;
import com.example.e_commerce.model.dtos.OrdersAddDTO;
import com.example.e_commerce.model.dtos.OrdersGetDTO;
import com.example.e_commerce.model.dtos.ResponseDTO.OrdersResponseDTO;
import com.example.e_commerce.model.entities.Orders;
import com.example.e_commerce.repositories.OrdersRepository;
import com.example.e_commerce.services.OrdersService;
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
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    ModelMapper modelMapper;

    private static final Logger logger = LoggerFactory.getLogger(UsersServiceImpl.class);

    @Override
    public OrdersGetDTO addOrders(OrdersGetDTO ordersGetDTO) throws ResourceAlreadyExistException {

        Orders orders1 = ordersRepository.findOrdersById(ordersGetDTO.getOrderId());
        if (orders1 != null){
            throw new ResourceAlreadyExistException("orders with id " + orders1.getOrderId() + " already exist");
        }

        Orders orders2 = new Orders();
        orders2.setEmail(ordersGetDTO.getEmail());
        orders2.setOrderDate(ordersGetDTO.getOrderDate());
        orders2.setOrderStatus(ordersGetDTO.getOrderStatus());
        orders2.setTotalAmount(ordersGetDTO.getTotalAmount());

        ordersRepository.save(orders2);

        return convertToGetDTO(orders2);

    }


    @Override
    public OrdersGetDTO getOrdersById(UUID orders_id, OrdersGetDTO ordersGetDTO) throws ResourceNotExistException {

        Orders orders1 = ordersRepository.findOrdersById(orders_id);
        if (orders1 == null)
            throw new ResourceNotExistException("order with that id not exist!");

        return convertToGetDTO(orders1);
    }

    @Override
    public OrdersResponseDTO getAllOrdersByPagination(int page_number, int page_size, String sort_order, String sort_by){
        //sorting data
        Sort sortByAndOrders = sort_order.equalsIgnoreCase("ASC")? Sort.by(sort_by).ascending() :
                Sort.by(sort_by).descending();
        //create request
        Pageable detail = PageRequest.of(page_number, page_size, sortByAndOrders);
        //get data from repo
        Page<Orders> ordersPage = ordersRepository.findAll(detail);
        //data to list
        List<Orders> ordersList = ordersPage.getContent();
        //convert DTO
        List<OrdersGetDTO> ordersGetDTOList = ordersList.stream().map(u -> modelMapper.map(u, OrdersGetDTO.class)
        ).collect(Collectors.toList());
        //response
        OrdersResponseDTO ordersResponseDTO = new OrdersResponseDTO();
        ordersResponseDTO.setContent(ordersGetDTOList);
        ordersResponseDTO.setPage_number(ordersPage.getNumber());
        ordersResponseDTO.setPage_size(ordersPage.getSize());
        ordersResponseDTO.setTotal_element(ordersPage.getTotalElements());
        ordersResponseDTO.setTotal_page(ordersPage.getTotalPages());
        ordersResponseDTO.setLast_page(ordersPage.isLast());

        return ordersResponseDTO;
    }

    @Override
    public void updateOrdersById(UUID order_id, OrdersAddDTO ordersAddDTO) throws ResourceNotExistException {

        Orders checkExist = ordersRepository.findOrdersById(order_id);
        if (checkExist == null){
            throw new ResourceNotExistException("orders id not exist");
        }
        checkExist.setEmail(ordersAddDTO.getEmail());
        checkExist.setOrderDate(ordersAddDTO.getOrderDate());
        checkExist.setOrderStatus(ordersAddDTO.getOrderStatus());
        checkExist.setTotalAmount(ordersAddDTO.getTotalAmount());
        checkExist.setPayments(ordersAddDTO.getPayments());

        ordersRepository.save(checkExist);
    }

    @Override
    public void deleteOrdersById(UUID order_id) throws ResourceNotExistException {

        Orders orders = ordersRepository.findOrdersById(order_id);
        if (orders == null){
            String message = "orders id not exist";
            logger.error(message);
            throw new ResourceNotExistException(message);
        }
        ordersRepository.delete(orders);
    }

    private OrdersGetDTO convertToGetDTO(Orders orders){
        return modelMapper.map(orders, OrdersGetDTO.class);
    }

    private OrdersAddDTO convertToAddDTO(Orders orders){
        return modelMapper.map(orders, OrdersAddDTO.class);
    }

    private Orders convertFromDTO(OrdersGetDTO ordersGetDTO){
        return modelMapper.map(ordersGetDTO, Orders.class);
    }

    private Orders convertFromDTO(OrdersAddDTO ordersAddDTO){
        return modelMapper.map(ordersAddDTO, Orders.class);
    }

}
