package com.example.e_commerce.model.dtos;

import com.example.e_commerce.model.entities.Payments;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import java.util.Date;

@RequiredArgsConstructor
@Getter
@Setter
public class OrdersAddDTO {
    @Email(message = "must be filled in with email format")
    private String email;
    @Column(name = "order_date")
    private Date orderDate;
    @Column(name = "order_status")
    private String orderStatus;
    @Column(name = "total_amount")
    @Min(value = 0)
    private int totalAmount;
    private Payments payments;
}
