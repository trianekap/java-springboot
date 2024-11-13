package com.example.e_commerce.services.implement;

import com.example.e_commerce.exceptions.NonExitenceResourceException;
import com.example.e_commerce.exceptions.ResourceAlreadyExistException;
import com.example.e_commerce.exceptions.ResourceNotExistException;
import com.example.e_commerce.model.dtos.CategoriesDTO;
import com.example.e_commerce.model.dtos.ProductsAddDTO;
import com.example.e_commerce.model.dtos.ProductsGetDTO;
import com.example.e_commerce.model.dtos.ResponseDTO.CategoriesResponseDTO;
import com.example.e_commerce.model.entities.Categories;
import com.example.e_commerce.model.entities.Products;
import com.example.e_commerce.repositories.CategoriesRepository;
import com.example.e_commerce.services.CategoriesService;
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
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoriesServiceImpl implements CategoriesService {
    @Autowired
    CategoriesRepository categoriesRepository;

    @Autowired
    ModelMapper modelMapper;

    private static final Logger logger = LoggerFactory.getLogger(UsersServiceImpl.class);

    @Override
    public CategoriesDTO addCategories(CategoriesDTO categoriesDTO) throws ResourceAlreadyExistException {

        Categories checkCategories = categoriesRepository.findByCategoryName(categoriesDTO.getCategoryName());

        if (checkCategories != null){
            throw new ResourceAlreadyExistException("Resource with that name already exist");
        }

        Categories categories2 = new Categories();
        categories2.setCategoryName(categoriesDTO.getCategoryName());

        categoriesRepository.save(categories2);

        return convertToCategoriesDTO(categories2);

    }

    @Override
    public CategoriesResponseDTO getAllCategoriesByPagination(int page_number, int page_size, String sort_order, String sort_by){
        //sorting data
        Sort sortByAndOrders = sort_order.equalsIgnoreCase("ASC")? Sort.by(sort_by).ascending() :
                Sort.by(sort_by).descending();
        //create request
        Pageable detail = PageRequest.of(page_number, page_size, sortByAndOrders);
        //get data from repo
        Page<Categories> categoriesPage = categoriesRepository.findAll(detail);
        //data to list
        List<Categories> categoriesList = categoriesPage.getContent();
        //convert DTO
        List<CategoriesDTO> categoriesDTOList = categoriesList.stream().map(u -> modelMapper.map(u, CategoriesDTO.class)
        ).collect(Collectors.toList());
        //response
        CategoriesResponseDTO categoriesResponseDTO = new CategoriesResponseDTO();
        categoriesResponseDTO.setContent(categoriesDTOList);
        categoriesResponseDTO.setPage_number(categoriesPage.getNumber());
        categoriesResponseDTO.setPage_size(categoriesPage.getSize());
        categoriesResponseDTO.setTotal_element(categoriesPage.getTotalElements());
        categoriesResponseDTO.setTotal_page(categoriesPage.getTotalPages());
        categoriesResponseDTO.setLast_page(categoriesPage.isLast());

        return categoriesResponseDTO;
    }

    @Override
    public CategoriesDTO getCategoriesById(UUID categories_id, CategoriesDTO categoriesDTO) throws ResourceNotExistException {

        Categories checkExist = categoriesRepository.findCategoriesById(categories_id);
        if (checkExist == null){
            throw new ResourceNotExistException("resource with id not existed");
        }
        return convertToCategoriesDTO(checkExist);
    }

    @Override
    public void updateCategoriesById(UUID categories_id, CategoriesDTO categoriesDTO) throws ResourceNotExistException, ResourceAlreadyExistException{
        Categories categories = categoriesRepository.findCategoriesById(categories_id);
        if (categories == null){
            throw new ResourceNotExistException("Resource with that id not existed");
        }

        categories.setCategoryName(categoriesDTO.getCategoryName());

        categoriesRepository.save(categories);
    }

    @Override
    public void deleteCategoriesById(UUID categories_id) throws ResourceNotExistException{

        Categories categories = categoriesRepository.findCategoriesById(categories_id);
        if (categories == null){
            throw new ResourceNotExistException("Resource with that id not existed");
        }

        categoriesRepository.delete(categories);
    }

    private CategoriesDTO convertToCategoriesDTO(Categories categories){
        return modelMapper.map(categories, CategoriesDTO.class);
    }

    private Categories convertFromDTO(CategoriesDTO categoriesDTO){
        return modelMapper.map(categoriesDTO, Categories.class);
    }
}
