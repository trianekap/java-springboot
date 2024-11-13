package com.codean.CoffeShop.models.dtos;

import com.codean.CoffeShop.models.entities.Order;
import com.codean.CoffeShop.models.entities.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import java.util.UUID;

@Data
public class OrderItemGetDTO {
    private UUID OrderItemId;
    private Integer quantity;
    private Double price;

    private ProductGetDTO product;

    private OrderGetDTO order;

}
