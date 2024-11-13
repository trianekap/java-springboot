package com.example.e_commerce.services.implement;

import com.example.e_commerce.exceptions.NonExitenceResourceException;
import com.example.e_commerce.exceptions.ResourceAlreadyExistException;
import com.example.e_commerce.exceptions.ResourceNotExistException;
import com.example.e_commerce.model.dtos.*;
import com.example.e_commerce.model.dtos.ResponseDTO.CartItemsResponseDTO;
import com.example.e_commerce.model.entities.*;
import com.example.e_commerce.repositories.CartItemsRepository;
import com.example.e_commerce.services.CartItemsActivityLog;
import com.example.e_commerce.services.CartItemsService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CartItemsServiceImpl implements CartItemsService {

    @Autowired
    CartItemsRepository cartItemsRepository;

    @Autowired
    CartItemsActivityLog cartItemsActivityLog2;

    @Autowired
    ModelMapper modelMapper;


    private static final Logger logger = LoggerFactory.getLogger(UsersServiceImpl.class);

    @Override
    public CartItemsGetDTO addCartItems(CartItemsAddDTO cartItemsAddDTO) throws ResourceAlreadyExistException {

        CartItems convertedCartItems = convertFromDTO(cartItemsAddDTO);

        CartItems cartItems1 = new CartItems();
        cartItems1.setDiscount(cartItemsAddDTO.getDiscount());
        cartItems1.setProductPrice(cartItemsAddDTO.getProductPrice());
        cartItems1.setQuantity(cartItemsAddDTO.getQuantity());
        cartItems1.setCarts(cartItemsAddDTO.getCarts());
        cartItems1.setProducts(cartItemsAddDTO.getProducts());

        cartItems1.setCreatedAt(ZonedDateTime.now(ZoneId.of("Asia/Jakarta")));
        CartItemsActivityLogEntity cartItemsActivityLogEntity = new CartItemsActivityLogEntity();
        cartItemsActivityLogEntity.setActivityDate(ZonedDateTime.now(ZoneId.of("Asia/Jakarta")));
        cartItemsActivityLogEntity.setActivityType(CartItemsActivityLogEntity.ActivityType.CREATED_CART_ITEMS.getType());

        cartItemsRepository.save(cartItems1);
        cartItemsActivityLog2.addLogCartItems(cartItemsActivityLogEntity);

        return convertToCartItemsGetDTO(cartItems1);

    }

    @Override
    public CartItemsResponseDTO getAllCartItemsPagination(int page_number, int page_size, String sort_order, String sort_by){
        //sorting data
        Sort sortByAndOrders = sort_order.equalsIgnoreCase("ASC")? Sort.by(sort_by).ascending() :
                Sort.by(sort_by).descending();
        //create request
        Pageable detail = PageRequest.of(page_number, page_size, sortByAndOrders);
        //get data from repo
        Page<CartItems> cartItemsPage = cartItemsRepository.findAll(detail);
        //data to list
        List<CartItems> cartItemsList = cartItemsPage.getContent();
        //convert DTO
        List<CartItemsGetDTO> cartItemsGetDTOList = cartItemsList.stream().map(u -> modelMapper.map(u, CartItemsGetDTO.class)
        ).collect(Collectors.toList());
        //response
        CartItemsResponseDTO cartItemsResponseDTO = new CartItemsResponseDTO();
        cartItemsResponseDTO.setContent(cartItemsGetDTOList);
        cartItemsResponseDTO.setPage_number(cartItemsPage.getNumber());
        cartItemsResponseDTO.setPage_size(cartItemsPage.getSize());
        cartItemsResponseDTO.setTotal_element(cartItemsPage.getTotalElements());
        cartItemsResponseDTO.setTotal_page(cartItemsPage.getTotalPages());
        cartItemsResponseDTO.setLast_page(cartItemsPage.isLast());

        return cartItemsResponseDTO;
    }

    @Override
    public CartItemsGetDTO getCartItemsById(UUID cart_item_id) throws ResourceNotExistException {

        CartItems cartItems = getExistenceCartItems(cart_item_id);

        return convertToCartItemsGetDTO(cartItems);
    }

    @Override
    public void updateCartItemsById(UUID cart_item_id, CartItemsAddDTO cartItemsAddDTO) throws ResourceNotExistException{
        CartItems cartItemsDTO = modelMapper.map(cartItemsAddDTO, CartItems.class);
        CartItems cartItems = getExistenceCartItems(cart_item_id);

        cartItems.setDiscount(cartItemsDTO.getDiscount());
        cartItems.setProductPrice(cartItemsDTO.getDiscount());
        cartItems.setQuantity(cartItemsDTO.getQuantity());
        cartItems.setModified_at(ZonedDateTime.now(ZoneId.of("Asia/Jakarta")));

        CartItemsActivityLogEntity cartItemsActivityLogEntity = new CartItemsActivityLogEntity();

        cartItemsActivityLogEntity.setActivityDate(ZonedDateTime.now(ZoneId.of("Asia/Jakarta")));
        cartItemsActivityLogEntity.setActivityType(UsersActivityLogEntity.ActivityType.MODIFIED_USER.getType());

        cartItemsRepository.save(cartItems);
        cartItemsActivityLog2.updateLogCartItems(cartItemsActivityLogEntity);
    }

    @Override
    public void deleteCartItemsById(UUID cart_items_id) throws ResourceNotExistException{

        CartItems cartItems1 = getExistenceCartItems(cart_items_id);

        cartItemsRepository.delete(cartItems1);

    }

    private void checkDuplicate(UUID cartItemId) throws ResourceAlreadyExistException{
        CartItems existedCartItems = cartItemsRepository.findCartItemsById(cartItemId);
        if (!existedCartItems.getCartItemId().equals(cartItemId)){
            String errorMsg = "Resource CartItems with same id already exist!";
            logger.error(errorMsg);
            throw new ResourceAlreadyExistException(errorMsg);
        }
    }

    private CartItems getExistenceCartItems(UUID cartItemsId) throws ResourceNotExistException {
        CartItems existedCartItems = cartItemsRepository.findCartItemsById(cartItemsId);
        if (existedCartItems == null){
            String errorMessage = "Resource with user id " + cartItemsId + " doesn't exist!";
            logger.error(errorMessage);
            throw new ResourceNotExistException(errorMessage);
        }

        return existedCartItems;
    }

    private int getTotalBuy(CartItems cartItems){
        return cartItems.getProductPrice() - cartItems.getDiscount();
    }

    private CartItemsGetDTO convertToCartItemsGetDTO(CartItems cartItems){
        return modelMapper.map(cartItems, CartItemsGetDTO.class);
    }

    private CartItemsAddDTO convertToCartItemsAddDTO(CartItems cartItems){
        return modelMapper.map(cartItems, CartItemsAddDTO.class);
    }

    private CartItems convertFromDTO(CartItemsAddDTO cartItemsAddDTO){
        return modelMapper.map(cartItemsAddDTO, CartItems.class);
    }

    private CartItems convertFromDTO(CartItemsGetDTO cartItemsGetDTO){
        return modelMapper.map(cartItemsGetDTO, CartItems.class);
    }
}
