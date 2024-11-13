package com.example.e_commerce.model.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.*;
import java.util.UUID;

@Getter
@Setter
public class UsersAddDTO {

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Size(min = 3, message = "first name should have at least 3 characters")
    private String firstName;
    @NotEmpty
    @Size(min = 3, message = "last name should have at least 3 characters")
    private String lastName;

    @NotBlank
    @Size(min = 10, max = 13, message = "mobile number minimal 10 digits and not then 13 digits")
    @Pattern(regexp="^(\\+62|62|0)8[1-9]\\d{8,10}$", message = "mobile number is not valid!")
    @Column(unique = true)
    private String mobileNumber;

    @NotEmpty
    @Size(min = 8, message = "password should have at 8 characters")
    private String password;
}
