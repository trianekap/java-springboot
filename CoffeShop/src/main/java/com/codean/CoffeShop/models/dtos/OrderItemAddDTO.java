package com.codean.CoffeShop.models.dtos;

import com.codean.CoffeShop.models.entities.Order;
import com.codean.CoffeShop.models.entities.Product;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.UUID;

@Data
public class OrderItemAddDTO {

//    private UUID OrderItemId;

    @Min(value = 1)
    private Integer quantity;

    @Min(value = 1)
    private Double price;

    private ProductGetDTO product;

    private OrderGetDTO order;
}
