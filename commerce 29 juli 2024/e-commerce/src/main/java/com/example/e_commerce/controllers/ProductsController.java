package com.example.e_commerce.controllers;

import com.example.e_commerce.exceptions.NonExitenceResourceException;
import com.example.e_commerce.exceptions.ResourceAlreadyExistException;
import com.example.e_commerce.exceptions.ResourceNotExistException;
import com.example.e_commerce.model.dtos.ProductsAddDTO;
import com.example.e_commerce.model.dtos.ProductsGetDTO;
import com.example.e_commerce.model.dtos.ResponseDTO.ProductsResponseDTO;
import com.example.e_commerce.model.entities.Products;
import com.example.e_commerce.model.responses.ApiResponses;
import com.example.e_commerce.model.responses.ResponseMessages;
import com.example.e_commerce.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/")
public class ProductsController {
    @Autowired
    ProductsService productsService;

    @PostMapping("/addproduct")
    public ApiResponses<Object> addProducts(@Valid @RequestBody ProductsAddDTO productsAddDTO) throws ResourceAlreadyExistException{

        ProductsGetDTO products = productsService.addProducts(productsAddDTO);


        return ApiResponses.success(HttpStatus.OK, ResponseMessages.RESOURCE_CREATED.getMessages(), null);
    }

    @GetMapping("/getallproductspagination")
    public ResponseEntity<ProductsResponseDTO> getUsersByPagination(
            @RequestParam int page_number,
            @RequestParam int page_size,
            @RequestParam String sort_order,
            @RequestParam(defaultValue = "productname") String sort_by
    ){

        ProductsResponseDTO productsResponseDTO = productsService.getAllProductsPagination(page_number, page_size, sort_order, sort_by);

        return new ResponseEntity<ProductsResponseDTO>(productsResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/getproductbyid/{product_id}")
    public ApiResponses<Object> getCartsById(@PathVariable UUID product_id, ProductsGetDTO productsGetDTO) throws ResourceNotExistException{
        ProductsGetDTO productsGetDTO1 = productsService.getProductsById(product_id, productsGetDTO);
        return ApiResponses.success(HttpStatus.OK, ResponseMessages.RESOURCE_FETCHED.getMessages(), productsGetDTO1);
    }

    @GetMapping("/getproductbykeyword")
    public ApiResponses<Object> getProductByKeyword(@RequestParam String keyword) throws ResourceNotExistException{
        List<ProductsGetDTO> productsList = productsService.findProductsByKeyword(keyword);

        if (productsList.isEmpty()){
            return ApiResponses.fail(HttpStatus.NOT_FOUND, ResponseMessages.RESOURCE_NOTFOUND.getMessages());
        }

        return ApiResponses.success(HttpStatus.OK, ResponseMessages.RESOURCE_FETCHED.getMessages(), productsList);
    }

    @PutMapping("/updateproductbyid/{product_id}")
    public ApiResponses<Object> updateCartsById(@PathVariable UUID product_id, @RequestBody ProductsAddDTO productsAddDTO) throws ResourceNotExistException, ResourceAlreadyExistException{
        productsService.updateProductsById(product_id, productsAddDTO);
        return ApiResponses.success(HttpStatus.ACCEPTED, ResponseMessages.RESOURCE_MODIFIED.getMessages(), null);
    }

    @DeleteMapping("/deleteproduct/{product_id}")
    public ApiResponses<Products> deleteCartsById(@PathVariable UUID product_id){
        try{
            productsService.deleteProductsById(product_id);
        } catch (com.example.e_commerce.exceptions.ResourceNotExistException e) {
            throw new RuntimeException(e);
        }

        return ApiResponses.success(HttpStatus.OK, ResponseMessages.RESOURCE_DELETED.getMessages(), null);
    }
}
