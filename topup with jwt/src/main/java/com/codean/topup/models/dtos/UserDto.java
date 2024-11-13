package com.codean.topup.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private Long idUser;
    @NotBlank(message = "nama user cannot be blank!")
    @Pattern(regexp = "[a-zA-Z\\s]{3,500}", message = "nama user must be characters only and at least 3 characters long!")
    private String namaUser;
    @Email
    @NotBlank(message = "email cannot be blank!")
    private String email;
    @Size(min = 8, message = "password at least have 8 character!")
    @NotBlank(message = "password cannot be blank!")
    private String password;
}
