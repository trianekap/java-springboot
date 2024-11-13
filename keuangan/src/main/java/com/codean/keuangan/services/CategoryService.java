package com.codean.keuangan.services;

import com.codean.keuangan.exceptions.ResourceNotExistException;
import com.codean.keuangan.models.dtos.CategoryDto;
import com.codean.keuangan.models.dtos.updatedto.CategoryUpdateDto;
import com.codean.keuangan.models.dtos.pageresult.PageResult;

import java.util.List;

public interface CategoryService {
    PageResult<CategoryDto> getAllByPage(int page, int pageSize);

    List<CategoryDto> searchByName(String name);

    List<CategoryDto> getAll() throws ResourceNotExistException;
    CategoryDto getId(int id) throws ResourceNotExistException;
    void create(CategoryDto categoryDto);

    void update(int id, CategoryUpdateDto categoryUpdateDto) throws ResourceNotExistException;

    void delete(int id) throws ResourceNotExistException;
}
