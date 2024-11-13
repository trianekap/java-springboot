package com.example.e_commerce.model.dtos;

import com.example.e_commerce.model.entities.Payments;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class OrdersGetDTO {
    private UUID orderId;
    private String email;
    private Date orderDate;
    private String orderStatus;
    private int totalAmount;
}
