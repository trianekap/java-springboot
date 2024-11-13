package com.example.mybatis.mapper;

import com.example.mybatis.model.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {

    List<Product> findAllProduct();

    void createProduct(Product product);

    void updatePrice(Integer id, int price);

    void deleteProduct(int id);

}
