package com.codean.keuangan.models.dtos.updatedto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountUpdateDto {
    @NotNull(message = "user id cannot be null!")
    private int userId;
    @NotBlank(message = "account name cannot be blank!")
    private String accountName;
    @NotNull(message = "balance cannot be null!")
    private int balance;
}
