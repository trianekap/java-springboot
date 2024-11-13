package com.codean.keuangan.models.dtos.updatedto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserUpdateDto {
    @NotBlank(message = "username cannot be blank!!!")
    @Size(min = 3, message = "minimum character for username is 3!!!")
    private String username;
    @NotBlank(message = "password cannot be blank!!!")
    @Size(min = 8, message = "minimum character for password is 8!!!")
    private String password;
}
