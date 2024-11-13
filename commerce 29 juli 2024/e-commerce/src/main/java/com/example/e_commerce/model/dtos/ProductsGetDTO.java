package com.example.e_commerce.model.dtos;

import com.example.e_commerce.model.entities.Categories;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProductsGetDTO {
    private UUID productId;
    private String description;
    private int discount;
    private String image;
    private int price;
    private String productName;
    private int quantity;
    private int specialPrice;
    private Categories categories;
}
