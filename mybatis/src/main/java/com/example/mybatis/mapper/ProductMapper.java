package com.example.mybatis.mapper;

import com.example.mybatis.model.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {
    @Select("SELECT id, name, price FROM product ORDER BY id")
    List<Product> findAllProduct();

    @Insert("INSERT INTO product (id, name, price) values(#{id}, #{name}, #{price})")
    void createProduct(Product product);

    @Update("UPDATE product SET price = #{price} WHERE id = #{id}")
    void updatePrice(Integer id, int price);

    @Delete("DELETE FROM product WHERE id = #{id}")
    void deleteProduct(int id);

}
