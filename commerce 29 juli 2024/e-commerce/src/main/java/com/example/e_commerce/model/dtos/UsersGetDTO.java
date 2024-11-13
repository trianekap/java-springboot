package com.example.e_commerce.model.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Getter
@Setter
public class UsersGetDTO {
    private UUID userId;
    private String email;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String password;
}
