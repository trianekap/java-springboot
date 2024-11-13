package com.codean.keuangan.models.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    @NotBlank(message = "username cannot be blank!!!")
    @Size(min = 3, message = "minimum character for username is 3!!!")
    private String username;
    @NotBlank(message = "password cannot be blank!!!")
    @Size(min = 8, message = "minimum character for password is 8!!!")
    private String password;

}
