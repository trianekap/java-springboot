package com.codean.keuangan.mappers;

import com.codean.keuangan.models.dtos.CategoryDto;
import com.codean.keuangan.models.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CategoryMapper {

    List<CategoryDto> getAllByPage(Map<String, Object> params);

    List<CategoryDto> searchByName(String name);

    Long countCategory();

    List<Category> getAll();
    Category getId(int id);
    void create(Category category);
    void update(Category category);
    void delete(int id);
}
