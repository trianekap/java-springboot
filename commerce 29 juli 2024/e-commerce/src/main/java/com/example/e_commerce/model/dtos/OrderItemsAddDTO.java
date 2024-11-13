package com.example.e_commerce.model.dtos;

import com.example.e_commerce.model.entities.Orders;
import com.example.e_commerce.model.entities.Products;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItemsAddDTO {

    @Min(value = 1000)
    private int discount;
    @Min(value = 0)
    private int orderedProductPrice;
    @Min(value = 0)
    private int quantity;
    private Orders orders;
    private Products products;
}
