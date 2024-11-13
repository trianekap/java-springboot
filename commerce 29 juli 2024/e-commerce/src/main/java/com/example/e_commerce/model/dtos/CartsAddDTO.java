package com.example.e_commerce.model.dtos;

import com.example.e_commerce.model.entities.Carts;
import com.example.e_commerce.model.entities.Products;
import com.example.e_commerce.model.entities.Users;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartsAddDTO {
    @NotNull
    @Min(value = 10000, message = "total price minimal 10000")
    private int totalPrice;
    private Users users;

}
