package com.example.e_commerce.services.implement;

import com.example.e_commerce.exceptions.ResourceAlreadyExistException;
import com.example.e_commerce.exceptions.ResourceNotExistException;
import com.example.e_commerce.model.dtos.ProductsAddDTO;
import com.example.e_commerce.model.dtos.ProductsGetDTO;
import com.example.e_commerce.model.dtos.ResponseDTO.ProductsResponseDTO;
import com.example.e_commerce.model.dtos.ResponseDTO.UsersResponseDTO;
import com.example.e_commerce.model.dtos.UsersGetDTO;
import com.example.e_commerce.model.entities.Products;
import com.example.e_commerce.model.entities.ProductsActivityLogEntity;
import com.example.e_commerce.model.entities.Users;
import com.example.e_commerce.repositories.ProductsRepository;
import com.example.e_commerce.services.ProductsActivityLog;
import com.example.e_commerce.services.ProductsService;
import lombok.RequiredArgsConstructor;
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
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor //agar tidak perlu menulis @autowired
public class ProductsServiceImpl implements ProductsService {
    ProductsRepository productsRepository;

    ProductsActivityLog productsActivityLog2;

    ModelMapper modelMapper;

    private static final Logger logger = LoggerFactory.getLogger(UsersServiceImpl.class);

    @Override
    public ProductsGetDTO addProducts(ProductsAddDTO productsAddDTO) throws ResourceAlreadyExistException {

        Products checkProducts = productsRepository.findProductsByName(productsAddDTO.getProductName());

        if (checkProducts != null){
            throw new ResourceAlreadyExistException("Resource with that name already exist");
        }

        Products products1 = new Products();
        products1.setDescription(productsAddDTO.getDescription());
        products1.setDiscount(productsAddDTO.getDiscount());
        products1.setImage(productsAddDTO.getImage());
        products1.setPrice(productsAddDTO.getPrice());
        products1.setProductName(productsAddDTO.getProductName());
        products1.setQuantity(productsAddDTO.getQuantity());
        products1.setSpecialPrice(productsAddDTO.getSpecialPrice());
        products1.setCategories(productsAddDTO.getCategories());
        products1.setCreated_at(ZonedDateTime.now(ZoneId.of("Asia/Jakarta")));

        ProductsActivityLogEntity productsActivityLogEntity = new ProductsActivityLogEntity();
        productsActivityLogEntity.setProducts(products1);
        productsActivityLogEntity.setActivityDate(ZonedDateTime.now(ZoneId.of("Asia/Jakarta")));
        productsActivityLogEntity.setActivityType(ProductsActivityLogEntity.ActivityType.CREATED_PRODUCTS.getType());

        productsRepository.save(products1);
        productsActivityLog2.addLogProducts(productsActivityLogEntity);

        return convertToProductsGetDTO(products1);

    }

    @Override
    public ProductsResponseDTO getAllProductsPagination(int page_number, int page_size, String sort_order, String sort_by){
        //sorting data
        Sort sortByAndOrders = sort_order.equalsIgnoreCase("ASC")? Sort.by(sort_by).ascending() :
                Sort.by(sort_by).descending();
        //create request
        Pageable detail = PageRequest.of(page_number, page_size, sortByAndOrders);
        //get data from repo
        Page<Products> productsPage = productsRepository.findAll(detail);
        //data to list
        List<Products> productsList = productsPage.getContent();
        //convert DTO
        List<ProductsGetDTO> productsGetDTOList = productsList.stream()
                .map(u -> modelMapper.map(u, ProductsGetDTO.class)
        ).collect(Collectors.toList());
        //response
        ProductsResponseDTO productsResponseDTO = new ProductsResponseDTO();
        productsResponseDTO.setContent(productsGetDTOList);
        productsResponseDTO.setPage_number(productsPage.getNumber());
        productsResponseDTO.setPage_size(productsPage.getSize());
        productsResponseDTO.setTotal_element(productsPage.getTotalElements());
        productsResponseDTO.setTotal_page(productsPage.getTotalPages());
        productsResponseDTO.setLast_page(productsPage.isLast());

        return productsResponseDTO;
    }

    @Override
    public ProductsGetDTO getProductsById(UUID product_id, ProductsGetDTO productsGetDTO) throws ResourceNotExistException {
        Products existedProduct = checkExistance(product_id);

        return convertToProductsGetDTO(existedProduct);
    }

    @Override
    public void updateProductsById(UUID product_id, ProductsAddDTO productsAddDTO) throws ResourceNotExistException, ResourceAlreadyExistException{
        Products existedProduct = checkExistance(product_id);

        Products converterProduct = convertFromDTO(productsAddDTO);
        Products checkProducts = productsRepository.findProductsByName(productsAddDTO.getProductName());

        if (checkProducts != null){
            throw new ResourceAlreadyExistException("resource already existtttt!!!");
        }

        existedProduct.setDescription(productsAddDTO.getDescription());
        existedProduct.setDiscount(productsAddDTO.getDiscount());
        existedProduct.setImage(productsAddDTO.getImage());
        existedProduct.setPrice(productsAddDTO.getPrice());
        existedProduct.setProductName(productsAddDTO.getProductName());
        existedProduct.setQuantity(productsAddDTO.getQuantity());
        existedProduct.setSpecialPrice(productsAddDTO.getSpecialPrice());
        existedProduct.setCategories(productsAddDTO.getCategories());
        existedProduct.setModified_at(ZonedDateTime.now(ZoneId.of("Asia/Jakarta")));

        ProductsActivityLogEntity productsActivityLogEntity = new ProductsActivityLogEntity();
        productsActivityLogEntity.setProducts(existedProduct);
        productsActivityLogEntity.setActivityDate(ZonedDateTime.now(ZoneId.of("Asia/Jakarta")));
        productsActivityLogEntity.setActivityType(ProductsActivityLogEntity.ActivityType.MODIFIED_PRODUCTS.getType());

        productsRepository.save(existedProduct);
        productsActivityLog2.updateLogProducts(productsActivityLogEntity);
    }

    @Override
    public void deleteProductsById(UUID product_id) throws ResourceNotExistException {

        Products products = checkExistance(product_id);

        productsRepository.delete(products);
    }

    @Override
    public List<ProductsGetDTO> findProductsByKeyword(String keyword) throws ResourceNotExistException {
        List<Products> productsList = productsRepository.findProductsByKeyword(keyword);

        if (productsList.isEmpty()){
            String errorMessage = "Resource with that keyword " + keyword + "doesn't exist";
            logger.error(errorMessage);
            throw new ResourceNotExistException(errorMessage);
        }

        return productsList.stream().map(this::convertToProductsGetDTO)
                .collect(Collectors.toList());
    }


    public void checkDuplicate(Products products) throws ResourceAlreadyExistException{
        Products checkDuplicate = productsRepository.findProductsByName(products.getProductName());
        if (checkDuplicate != null){
            String errorMessage = "Resource with that id is already exist";
            logger.error(errorMessage);
            throw  new ResourceAlreadyExistException(errorMessage);
        }
    }

    public Products checkExistance(UUID product_id) throws ResourceNotExistException{
        Products existedProduct = productsRepository.findProductsById(product_id);
        if (existedProduct == null){
            String errorMessage = "Resource with that id already exist";
            logger.error(errorMessage);
            throw new ResourceNotExistException(errorMessage);
        }

        return existedProduct;
    }

    private ProductsGetDTO convertToProductsGetDTO(Products products){
        return modelMapper.map(products, ProductsGetDTO.class);
    }

    private ProductsAddDTO convertToProductAddDTO(Products products){
        return modelMapper.map(products, ProductsAddDTO.class);
    }

    private Products convertFromDTO(ProductsAddDTO productsAddDTO){
        return modelMapper.map(productsAddDTO, Products.class);
    }

    private Products convertFromDTO(ProductsGetDTO productsGetDTO){
        return modelMapper.map(productsGetDTO, Products.class);
    }

}
