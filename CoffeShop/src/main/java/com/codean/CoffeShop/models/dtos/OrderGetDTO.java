package com.codean.CoffeShop.models.dtos;

import lombok.Data;

import java.util.Date;
import java.util.UUID;


@Data
public class OrderGetDTO {
    private UUID orderId;
    private Date orderDate;
    private Double totalAmount;
}
