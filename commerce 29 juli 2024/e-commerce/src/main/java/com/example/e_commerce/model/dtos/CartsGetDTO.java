package com.example.e_commerce.model.dtos;

import com.example.e_commerce.model.entities.Users;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartsGetDTO {
    private UUID cartId;
    private int totalPrice;
    private Users users;
}
