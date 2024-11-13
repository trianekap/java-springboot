package com.codean.keuangan.models.dtos.resultmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserAccount {
    private int id;
    private String username;
    private String accountName;
    private int balance;
}
