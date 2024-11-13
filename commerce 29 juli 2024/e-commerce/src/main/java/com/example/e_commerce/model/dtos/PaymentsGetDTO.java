package com.example.e_commerce.model.dtos;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PaymentsGetDTO {
    private UUID paymentId;
    private String paymentMethod;
}
