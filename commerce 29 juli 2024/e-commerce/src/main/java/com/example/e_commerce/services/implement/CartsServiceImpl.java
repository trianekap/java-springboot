package com.example.e_commerce.services.implement;

import com.example.e_commerce.exceptions.NonExitenceResourceException;
import com.example.e_commerce.exceptions.ResourceAlreadyExistException;
import com.example.e_commerce.exceptions.ResourceNotExistException;
import com.example.e_commerce.model.dtos.ResponseDTO.CartsResponseDTO;
import com.example.e_commerce.model.dtos.CartsAddDTO;
import com.example.e_commerce.model.dtos.CartsGetDTO;
import com.example.e_commerce.model.entities.Carts;
import com.example.e_commerce.model.entities.CartsActivityLogEntity;
import com.example.e_commerce.model.entities.UsersActivityLogEntity;
import com.example.e_commerce.repositories.CartsRepository;
import com.example.e_commerce.services.CartsActivityLog;
import com.example.e_commerce.services.CartsService;
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
public class CartsServiceImpl implements CartsService {

    @Autowired
    CartsRepository cartsRepository;

    @Autowired
    CartsActivityLog cartsActivityLog2;

    @Autowired
    ModelMapper modelMapper;


    private static final Logger logger = LoggerFactory.getLogger(UsersServiceImpl.class);

    @Override
    public CartsGetDTO addCarts(CartsAddDTO cartsAddDTO) throws ResourceAlreadyExistException {

        Carts convertedCarts = convertFromDTO(cartsAddDTO);

        Carts carts = new Carts();
        carts.setTotalPrice(cartsAddDTO.getTotalPrice());
        carts.setUsers(cartsAddDTO.getUsers());
        carts.setCreated_at(ZonedDateTime.now(ZoneId.of("Asia/Jakarta")));

        CartsActivityLogEntity cartsActivityLogEntity = new CartsActivityLogEntity();
        cartsActivityLogEntity.setActivityDate(ZonedDateTime.now(ZoneId.of("Asia/Jakarta")));
        cartsActivityLogEntity.setActivityType(CartsActivityLogEntity.ActivityType.CREATED_CARTS.getType());

        cartsRepository.save(carts);
        cartsActivityLog2.addLogCart(cartsActivityLogEntity);

        return convertToCartGetDTO(carts);

    }

    @Override
    public CartsGetDTO getCartsById(UUID cart_id, CartsGetDTO cartsGetDTO) throws ResourceNotExistException {

        Carts existedCarts = getExistence(cart_id);
        return convertToCartGetDTO(existedCarts);
    }

    @Override
    public CartsResponseDTO getAllCartsByPagination(int page_number, int page_size, String sort_order, String sort_by){
        //sorting data
        Sort sortByAndOrders = sort_order.equalsIgnoreCase("ASC")? Sort.by(sort_by).ascending() :
                Sort.by(sort_by).descending();
        //create request
        Pageable detail = PageRequest.of(page_number, page_size, sortByAndOrders);
        //get data from repo
        Page<Carts> cartsPage = cartsRepository.findAll(detail);
        //data to list
        List<Carts> cartsList = cartsPage.getContent();
        //convert DTO
        List<CartsGetDTO> cartsGetDTOList = cartsList.stream().map(u -> modelMapper.map(u, CartsGetDTO.class)
        ).collect(Collectors.toList());
        //response
        CartsResponseDTO cartResponseDTO = new CartsResponseDTO();
        cartResponseDTO.setContent(cartsGetDTOList);
        cartResponseDTO.setPage_number(cartsPage.getNumber());
        cartResponseDTO.setPage_size(cartsPage.getSize());
        cartResponseDTO.setTotal_element(cartsPage.getTotalElements());
        cartResponseDTO.setTotal_page(cartsPage.getTotalPages());
        cartResponseDTO.setLast_page(cartsPage.isLast());

        return cartResponseDTO;
    }

    @Override
    public void updateCartsById(UUID cart_id, CartsAddDTO cartsAddDTO) throws ResourceNotExistException{
        Carts existedCarts = getExistence(cart_id);
        Carts convertedCarts = convertFromDTO(cartsAddDTO);

        Carts checkCartsExist = cartsRepository.findCartsById(convertedCarts.getCartId());
        if (checkCartsExist != null){
            String message = "carts with that id not exist";
            logger.error(message);
            throw new ResourceNotExistException(message);
        }

        existedCarts.setTotalPrice(cartsAddDTO.getTotalPrice());
        existedCarts.setUsers(cartsAddDTO.getUsers());
        existedCarts.setModified_at(ZonedDateTime.now(ZoneId.of("Asia/Jakarta")));

        CartsActivityLogEntity cartsActivityLogEntity = new CartsActivityLogEntity();

        cartsActivityLogEntity.setActivityDate(ZonedDateTime.now(ZoneId.of("Asia/Jakarta")));
        cartsActivityLogEntity.setActivityType(UsersActivityLogEntity.ActivityType.MODIFIED_USER.getType());

        cartsRepository.save(existedCarts);
        cartsActivityLog2.updateLogCart(cartsActivityLogEntity);
    }

    @Override
    public void deleteCartsById(UUID cart_id) throws ResourceNotExistException{

        Carts existedCarts = getExistence(cart_id);

        cartsRepository.delete(existedCarts);

    }

    private Carts getExistence(UUID cartId) throws ResourceNotExistException {
        Carts existedCart = cartsRepository.findCartsById(cartId);
        if (existedCart == null){
            String errorMsg = "Resource with product id " + cartId + " doesn't exist!";
            logger.error(errorMsg);
            throw new ResourceNotExistException(errorMsg);
        }

        return existedCart;
    }

//    private CartsGetDTO convertToCartGetDTO(Carts carts){
//        return modelMapper.map(carts, CartsGetDTO.class);
//    }

//    private CartsAddDTO convertToCartsAddDTO(Carts carts){
//        return modelMapper.map(carts, CartsAddDTO.class);
//    }

    private CartsGetDTO convertToCartGetDTO(Carts carts){
        if (carts == null){
            return null;
        }

        CartsGetDTO dto = new CartsGetDTO();
        dto.setCartId(carts.getCartId());
        dto.setTotalPrice(carts.getTotalPrice());
        dto.setUsers(carts.getUsers());

        return dto;
    }

    private CartsAddDTO convertToCartsAddDTO(Carts carts){
        if (carts == null){
            return null;
        }

        CartsAddDTO dto = new CartsAddDTO();
        dto.setTotalPrice(dto.getTotalPrice());
        dto.setUsers(carts.getUsers());
        return dto;
    }

//    private Carts convertFromDTO(CartsAddDTO cartsAddDTO){
//        return modelMapper.map(cartsAddDTO, Carts.class);
//    }

    private Carts convertFromDTO(CartsAddDTO cartsAddDTO){
        if (cartsAddDTO == null){
            return null;
        }

        Carts carts = new Carts();
        carts.setTotalPrice(cartsAddDTO.getTotalPrice());
        carts.setUsers(cartsAddDTO.getUsers());
        return carts;
    }

    private Carts convertFromDTO(CartsGetDTO cartsGetDTO){
        return modelMapper.map(cartsGetDTO, Carts.class);
    }
}
