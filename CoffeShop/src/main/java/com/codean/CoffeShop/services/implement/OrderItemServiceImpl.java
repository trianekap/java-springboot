package com.codean.CoffeShop.services.implement;

import com.codean.CoffeShop.exception.ResourceAlreadyExistException;
import com.codean.CoffeShop.exception.ResourceNotExistException;
import com.codean.CoffeShop.models.dtos.OrderGetDTO;
import com.codean.CoffeShop.models.dtos.OrderItemAddDTO;
import com.codean.CoffeShop.models.dtos.OrderItemGetDTO;
import com.codean.CoffeShop.models.dtos.ProductGetDTO;
import com.codean.CoffeShop.models.dtos.responseDTO.OrderItemResponseDTO;
import com.codean.CoffeShop.models.entities.Order;
import com.codean.CoffeShop.models.entities.OrderItem;
import com.codean.CoffeShop.models.entities.Product;
import com.codean.CoffeShop.repositories.OrderItemRepository;
import com.codean.CoffeShop.services.OrderItemService;
import com.codean.CoffeShop.services.OrderService;
import com.codean.CoffeShop.services.ProductService;
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
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    ProductService productService;

    @Autowired
    OrderService orderService;

    @Autowired
    ModelMapper modelMapper;

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    public OrderItemGetDTO addOrderItem(OrderItemAddDTO orderItemAddDTO) throws ResourceAlreadyExistException, ResourceNotExistException {

        OrderItem convertedOrderItem = convertFromDTO(orderItemAddDTO);

        OrderItem orderItem = orderItemRepository.findOrderItemByUUID(orderItemAddDTO.getOrder().getOrderId());

        OrderItem orderItem2 = new OrderItem();
        orderItem2.setPrice(orderItemAddDTO.getPrice());
        orderItem2.setQuantity(orderItemAddDTO.getQuantity());

        OrderGetDTO orderGetDTO = orderService.getOrderById(convertedOrderItem.getOrder().getOrderId());
        orderItem2.setOrder(modelMapper.map(orderGetDTO, Order.class));

        ProductGetDTO productGetDTO = productService.getProductById(convertedOrderItem.getProduct().getProductId());
        orderItem2.setProduct(modelMapper.map(productGetDTO, Product.class));

        orderItemRepository.save(orderItem2);

        return convertToOrderItemGetDTO(orderItem2);
    }

    @Override
    public OrderItemResponseDTO getAllOrderItem(int page_number, int page_size, String sort_order, String sort_by){
        //sorting data
        Sort sortByAndOrders = sort_order.equalsIgnoreCase("ASC")? Sort.by(sort_by).ascending() :
                Sort.by(sort_by).descending();
        //create request
        Pageable detail = PageRequest.of(page_number, page_size, sortByAndOrders);
        //get data from repo
        Page<OrderItem> orderItemPage = orderItemRepository.findAll(detail);
        //data to list
        List<OrderItem> orderItemList = orderItemPage.getContent();
        //convert DTO
        List<OrderItemGetDTO> orderItemGetDTOList = orderItemList.stream().map(u -> modelMapper.map(u, OrderItemGetDTO.class)
        ).collect(Collectors.toList());
        //response
        OrderItemResponseDTO orderItemResponseDTO = new OrderItemResponseDTO();
        orderItemResponseDTO.setContent(orderItemGetDTOList);
        orderItemResponseDTO.setPage_number(orderItemPage.getNumber());
        orderItemResponseDTO.setPage_size(orderItemPage.getSize());
        orderItemResponseDTO.setTotal_element(orderItemPage.getTotalElements());
        orderItemResponseDTO.setLast_page(orderItemPage.isLast());

        return orderItemResponseDTO;
    }

    @Override
    public OrderItemGetDTO getOrderItemById(UUID order_item_id) throws ResourceNotExistException {

        OrderItem existedOrderItem = getExistence(order_item_id);
        return convertToOrderItemGetDTO(existedOrderItem);
    }

    @Override
    public void updateOrderItemById(UUID order_item_id, OrderItemAddDTO orderItemAddDTO) throws ResourceNotExistException, ResourceAlreadyExistException{

        OrderItem existedOrderItem = getExistence(order_item_id);
        OrderItem convertedOrderItem = convertFromDTO(orderItemAddDTO);


        existedOrderItem.setPrice(orderItemAddDTO.getPrice());
        existedOrderItem.setQuantity(orderItemAddDTO.getQuantity());

        orderItemRepository.save(existedOrderItem);
    }

    @Override
    public void deleteOrderItemById(UUID order_item_id) throws ResourceNotExistException{

        OrderItem existedOrderItem = getExistence(order_item_id);
        orderItemRepository.delete(existedOrderItem);

    }

    private void checkDuplicate(UUID orderItemId) throws ResourceAlreadyExistException{
        OrderItem existedOrderItem = orderItemRepository.findOrderItemByUUID(orderItemId);
        if (existedOrderItem != null){
            String errorMsg = "Resource order item with same id already exist!";
            logger.error(errorMsg);
            throw new ResourceAlreadyExistException(errorMsg);
        }
    }


    private OrderItem getExistence(UUID orderItemId) throws ResourceNotExistException {
        OrderItem existedProduct = orderItemRepository.findOrderItemByUUID(orderItemId);
        if (existedProduct == null){
            String errorMsg = "Resource with product id " + orderItemId + " doesn't exist!";
            logger.error(errorMsg);
            throw new ResourceNotExistException(errorMsg);
        }

        return existedProduct;
    }

    private OrderItemGetDTO convertToOrderItemGetDTO(OrderItem orderItem) {
        return modelMapper.map(orderItem, OrderItemGetDTO.class);
    }
    private OrderItemAddDTO convertToOrderItemAddDTO(OrderItem orderItem) {
        return modelMapper.map(orderItem, OrderItemAddDTO.class);
    }

    private OrderItem convertFromDTO(OrderItemAddDTO dto){
        return modelMapper.map(dto, OrderItem.class);
    }

    private OrderItem convertFromDTO(OrderItemGetDTO dto){
        return modelMapper.map(dto, OrderItem.class);
    }
}
