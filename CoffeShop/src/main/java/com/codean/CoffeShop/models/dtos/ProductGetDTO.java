package com.codean.CoffeShop.models.dtos;

import lombok.Data;
import java.util.UUID;

@Data
public class ProductGetDTO {
    private UUID productId;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
}
