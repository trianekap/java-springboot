package com.codean.CoffeShop.models.dtos;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
public class OrderAddDTO {
//    private UUID orderId;

    @Column(name = "order_date")
    @DateTimeFormat(pattern = "01-01-2024")
    private Date orderDate;

    @Column(name = "total_amount")
    private Double totalAmount;
}
