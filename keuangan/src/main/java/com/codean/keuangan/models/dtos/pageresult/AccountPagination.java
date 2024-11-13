package com.codean.keuangan.models.dtos.pageresult;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccountPagination {
    private int id;
    private int user_id;
    private String account_name;
    private int balance;
}
