package com.example.e_commerce.model.dtos;

import com.example.e_commerce.model.entities.Carts;
import com.example.e_commerce.model.entities.Products;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class CartItemsGetDTO {
    private UUID cartItemId;
    private int discount;
    private int productPrice;
    private int quantity;
    private Carts carts;
    private Products products;
}
