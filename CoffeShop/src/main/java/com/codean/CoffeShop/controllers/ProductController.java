package com.codean.CoffeShop.controllers;

import com.codean.CoffeShop.exception.ResourceAlreadyExistException;
import com.codean.CoffeShop.exception.ResourceNotExistException;
import com.codean.CoffeShop.models.dtos.ProductAddDTO;
import com.codean.CoffeShop.models.dtos.ProductGetDTO;
import com.codean.CoffeShop.models.dtos.responseDTO.ProductResponseDTO;
import com.codean.CoffeShop.models.entities.Product;
import com.codean.CoffeShop.models.responses.ApiResponses;
import com.codean.CoffeShop.models.responses.ResponseMessages;
import com.codean.CoffeShop.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/add")
    public ApiResponses<Object> addProduct(@Valid @RequestBody ProductAddDTO productAddDTO) throws ResourceAlreadyExistException, ResourceNotExistException {

        ProductGetDTO product = productService.addProduct(productAddDTO);

        return ApiResponses.success(HttpStatus.CREATED, ResponseMessages.RESOURCE_CREATED.getMessages(), null);
    }

    @GetMapping("/search")
    public ApiResponses<List<Product>> searchProduct(@RequestParam("query") String query){
        List<Product> getProduct = productService.searchProduct(query);
        return ApiResponses.success(HttpStatus.OK, ResponseMessages.RESOURCE_FETCHED.getMessages(), getProduct);
    }

    @GetMapping("/getallproductpagination")
    public ResponseEntity<ProductResponseDTO> getAllProductsPagination(
            @RequestParam int page_number,
            @RequestParam int page_size,
            @RequestParam String sort_order,
            @RequestParam(defaultValue = "productName") String sort_by
    ){

        ProductResponseDTO productsResponseDTO = productService.getAllProducts(page_number, page_size, sort_order, sort_by);

        return new ResponseEntity<ProductResponseDTO>(productsResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/getbyid/{product_id}")
    public ApiResponses<Object> getProductById(@PathVariable UUID product_id) throws ResourceNotExistException{

        ProductGetDTO product = productService.getProductById(product_id);

        return ApiResponses.success(HttpStatus.OK, ResponseMessages.RESOURCE_FETCHED.getMessages(), product);
    }


    @PutMapping("/updatebyid/{product_id}")
    public ApiResponses<Object> updateProductById(@PathVariable UUID product_id, @RequestBody ProductAddDTO productAddDTO) throws
            ResourceNotExistException, ResourceAlreadyExistException
            {
        productService.updateProductById(product_id, productAddDTO);
        return ApiResponses.success(HttpStatus.ACCEPTED, ResponseMessages.RESOURCE_MODIFIED.getMessages(), null);
    }

    @DeleteMapping("/deletebyid/{product_id}")
    public ApiResponses<Product> deleteProductById(@PathVariable UUID product_id) throws ResourceNotExistException{

        productService.deleteProductById(product_id);

        return ApiResponses.success(HttpStatus.OK, ResponseMessages.RESOURCE_DELETED.getMessages(), null);
    }



}
