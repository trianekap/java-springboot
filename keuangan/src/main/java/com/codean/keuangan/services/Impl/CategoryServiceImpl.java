package com.codean.keuangan.services.Impl;

import com.codean.keuangan.exceptions.ResourceNotExistException;
import com.codean.keuangan.mappers.CategoryMapper;
import com.codean.keuangan.models.dtos.CategoryDto;
import com.codean.keuangan.models.dtos.updatedto.CategoryUpdateDto;
import com.codean.keuangan.models.dtos.pageresult.PageResult;
import com.codean.keuangan.models.entity.Category;
import com.codean.keuangan.services.CategoryService;
import com.codean.keuangan.util.PaginationHelper;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);


    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public PageResult<CategoryDto> getAllByPage(int page, int pageSize) {
        if (page < 1) page = 1;
        if (pageSize < 1) pageSize = 10;

        int offset = (page - 1) * pageSize;

        Map<String, Object> params = new HashMap<>();
        params.put("offset", offset);
        params.put("limit", pageSize);
        List<CategoryDto> categoryDtoList = categoryMapper.getAllByPage(params);

        Long totalElement = categoryMapper.countCategory();

        int totalPage = PaginationHelper.calculateTotalPages(totalElement, pageSize);

        boolean isLastPage = page >= totalPage;

        PageResult<CategoryDto> pageResult = new PageResult<>();
        pageResult.setContent(categoryDtoList);
        pageResult.setPage_number(page);
        pageResult.setPage_size(pageSize);
        pageResult.setTotal_element(totalElement);
        pageResult.setTotal_page(totalPage);
        pageResult.setLast_page(isLastPage);

        return pageResult;
    }

    @Override
    public List<CategoryDto> searchByName(String name) {
        List<CategoryDto> categoryDtoList = categoryMapper.searchByName(name);

        return categoryDtoList.stream().collect(Collectors.toList());
    }

    @Override
    public List<CategoryDto> getAll() throws ResourceNotExistException {
        return categoryMapper.getAll().stream().map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto getId(int id) throws ResourceNotExistException {
        Category category = categoryMapper.getId(id);

        if (category == null){
            String message = "that id not exist";
            log.error(message);
            throw new ResourceNotExistException(message);
        }

        return toDto(category);
    }

    @Override
    public void create(CategoryDto categoryDto) {

        Category category = new Category();
        category.setName(categoryDto.getName());

        categoryMapper.create(category);
    }

    @Override
    public void update(int id, CategoryUpdateDto categoryUpdateDto) throws ResourceNotExistException {
        Category category = categoryMapper.getId(id);

        if (category == null){
            String message = "that id not exist";
            log.error(message);
            throw new ResourceNotExistException(message);
        }

        category.setName(categoryUpdateDto.getName());

        categoryMapper.update(category);
    }

    @Override
    public void delete(int id) throws ResourceNotExistException{
        Category category = categoryMapper.getId(id);
        if (category == null){
            String message = "that id not exist";
            log.error(message);
            throw new ResourceNotExistException(message);
        }

        categoryMapper.delete(id);
    }


    private CategoryDto toDto(Category category){
        return modelMapper.map(category, CategoryDto.class);
    }

    private Category toEntity(CategoryDto categoryDto){
        return modelMapper.map(categoryDto, Category.class);
    }


}
