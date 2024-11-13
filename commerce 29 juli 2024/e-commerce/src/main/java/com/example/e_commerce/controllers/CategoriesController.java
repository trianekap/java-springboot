package com.example.e_commerce.controllers;

import com.example.e_commerce.exceptions.ResourceAlreadyExistException;
import com.example.e_commerce.exceptions.ResourceNotExistException;
import com.example.e_commerce.model.dtos.CategoriesDTO;
import com.example.e_commerce.model.dtos.ResponseDTO.CategoriesResponseDTO;
import com.example.e_commerce.model.responses.ApiResponses;
import com.example.e_commerce.model.responses.ResponseMessages;
import com.example.e_commerce.services.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class CategoriesController {

    @Autowired
    CategoriesService categoriesService;

    @PostMapping("/addcategories")
    public ApiResponses<Object> addCategories(@Valid @RequestBody CategoriesDTO categoriesDTO) throws ResourceAlreadyExistException{

        CategoriesDTO categoriesDTO1 = categoriesService.addCategories(categoriesDTO);

        return ApiResponses.success(HttpStatus.OK, ResponseMessages.RESOURCE_CREATED.getMessages(), null);
    }

    @GetMapping("/getallcategoriespagination")
    public ResponseEntity<CategoriesResponseDTO> getCategoriesByPagination(
            @RequestParam int page_number,
            @RequestParam int page_size,
            @RequestParam String sort_order,
            @RequestParam(defaultValue = "mobilenumber") String sort_by
    ){

        CategoriesResponseDTO categoriesResponseDTO = categoriesService.getAllCategoriesByPagination(page_number, page_size, sort_order, sort_by);

        return new ResponseEntity<CategoriesResponseDTO>(categoriesResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/getcategoriesbyid/{categories_id}")
    public ApiResponses<Object> getCategoriesById(@PathVariable UUID categories_id, CategoriesDTO categoriesDTO) throws ResourceNotExistException {

        CategoriesDTO categoriesById = categoriesService.getCategoriesById(categories_id, categoriesDTO);

        return ApiResponses.success(HttpStatus.OK, ResponseMessages.RESOURCE_FETCHED.getMessages(), categoriesById);
    }

    @PutMapping("/updatecategoriesbyid/{categories_id}")
    public ApiResponses<Object> updateCategoriesById(@PathVariable UUID categories_id, @RequestBody CategoriesDTO categoriesDTO) throws ResourceAlreadyExistException,
            ResourceNotExistException {

        categoriesService.updateCategoriesById(categories_id, categoriesDTO);

        return ApiResponses.success(HttpStatus.ACCEPTED, ResponseMessages.RESOURCE_MODIFIED.getMessages(), null);
    }

    @DeleteMapping("/deletecategories/{categories_id}")
    public ApiResponses<Object> deleteCtegoriesById(@PathVariable UUID categories_id) throws ResourceNotExistException{

        categoriesService.deleteCategoriesById(categories_id);

        return ApiResponses.success(HttpStatus.OK, ResponseMessages.RESOURCE_DELETED.getMessages(), null);
    }


}



