package com.codean.keuangan.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private int id;
    @NotNull(message = "user id cannot be null!")
    private int userId;
    @NotBlank(message = "account name cannot be blank!")
    private String accountName;
    @NotNull(message = "balance cannot be null!")
    private int balance;

}
