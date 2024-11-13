package com.codean.keuangan.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private int id;
    @NotNull(message = " userId cannot be null!!!")
    @Min(value = 1, message = "must be input validation user id!")
    private int userId;
    @NotNull(message = "accountId cannot be null")
    @Min(value = 1, message = "must be input validation account id!")
    private int accountId;
    @NotNull(message = "cannot be null")
    @Min(value = 1, message = "must be input validation category id!")
    private int categoryId;
    @NotNull(message = "cannot be null")
    @Min(value = 1, message = "must be input validation transaction type id!")
    private int transactionTypeId;
    @NotNull(message = "date cannot be null")
    private LocalDate date;
    @NotBlank(message = "cannot be blank!!!")
    private String description;
    @Min(value = 1, message = "amount must be greater than 0")
    @NotNull(message = "cannot be null")
    private int amount;
}
