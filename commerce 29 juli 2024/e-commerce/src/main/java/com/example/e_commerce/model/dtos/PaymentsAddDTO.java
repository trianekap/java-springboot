package com.example.e_commerce.model.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class PaymentsAddDTO {
    private String paymentMethod;
}
