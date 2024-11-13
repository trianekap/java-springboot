package com.example.e_commerce.model.dtos;


import com.example.e_commerce.model.entities.Categories;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
public class ProductsAddDTO {

        @NotBlank
        @Length(min = 10)
        private String description;
        private int discount;
        private String image;
        @Min(value = 10000)
        private int price;
        @NotBlank
        @Length(min = 5)
        private String productName;
        @Min(value = 1)
        private int quantity;
        @Min(value = 10000)
        private int specialPrice;
        private Categories categories;



}
