package com.codean.CoffeShop.models.dtos;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;


@Data
public class ProductAddDTO {

//    private UUID productId;

    @NotNull(message = "product name can not null")
    @Column(unique = true)
    private String name;

    private String description;

    @Min(value = 1)
    private Double price;

    @Min(value = 0)
    private Integer stock;
}
