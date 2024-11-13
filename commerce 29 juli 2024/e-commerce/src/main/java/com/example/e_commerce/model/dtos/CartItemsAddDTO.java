package com.example.e_commerce.model.dtos;

import com.example.e_commerce.model.entities.Carts;
import com.example.e_commerce.model.entities.Products;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemsAddDTO {
    private int discount;
    @Min(value = 10000, message = "product price greater then 10000")
    private int productPrice;
    @NotNull
    @Min(value = 1, message = "at least quantity is 1")
    private int quantity;
    private Carts carts;
    private Products products;
}
