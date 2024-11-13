package com.codean.topup.models.entity;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long idUser;
    @NotBlank(message = "nama user cannot be blank!")
    @Pattern(regexp = "[a-zA-Z\\s]{3,500}",
            message = "nama user must be character only minimal 3 character!")
    private String namaUser;
    @Email
    @NotBlank(message = "email cannot be blank!")
    private String email;
    @Min(value = 8, message = "password at least have 8 character!")
    @NotBlank(message = "password cannot be blank!")
    private String password;
}
