package com.example.e_commerce.model.dtos;

import com.example.e_commerce.model.entities.Orders;
import com.example.e_commerce.model.entities.Products;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.constraints.Min;
import java.util.UUID;

@Getter
@Setter
public class OrderItemsGetDTO {
    private int discount;
    private int orderedProductPrice;
    private int quantity;
    private Orders orders;
    private Products products;
}
