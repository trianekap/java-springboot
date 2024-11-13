package com.codean.keuangan.controllers;

import com.codean.keuangan.exceptions.ResourceNotExistException;
import com.codean.keuangan.models.dtos.CategoryDto;
import com.codean.keuangan.models.dtos.updatedto.CategoryUpdateDto;
import com.codean.keuangan.models.dtos.pageresult.PageResult;
import com.codean.keuangan.services.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private static final Logger log = LoggerFactory.getLogger(AccountController.class);
    @Autowired
    CategoryService categoryService;

    @GetMapping("/getAllByPage")
    public PageResult<CategoryDto> getAllByPage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize) {

        return categoryService.getAllByPage(page, pageSize);
    }

    @PostMapping("/create")
    public String create(@RequestBody @Valid CategoryDto categoryDto){
        categoryService.create(categoryDto);
        return "category has been added";
    }

    @GetMapping("/getAll")
    public List<CategoryDto> getAll() throws ResourceNotExistException {
        List<CategoryDto> categoryDtoList = categoryService.getAll();
        if (categoryDtoList.isEmpty()){
            String message = "list is empty";
            log.info(message);
            throw new ResourceNotExistException(message);
        }

        return categoryService.getAll();
    }

    @GetMapping("/getId/{id}")
    public CategoryDto getId(@PathVariable int id) throws ResourceNotExistException{
        CategoryDto categoryDto = categoryService.getId(id);
        return categoryDto;
    }

    @GetMapping("/searchByName")
    public List<CategoryDto> searchByName(String name){
        List<CategoryDto> categoryDtoList = categoryService.searchByName(name);

        return categoryDtoList.stream().collect(Collectors.toList());
    }

    @PutMapping("/update/{id}")
    public List<CategoryDto> update(@PathVariable int id,
                                   @RequestBody @Valid CategoryUpdateDto categoryUpdateDto)
            throws ResourceNotExistException{

        categoryService.update(id, categoryUpdateDto);

        return getAll();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id) throws ResourceNotExistException{
        categoryService.delete(id);

        return "category has been deleted";
    }
}
