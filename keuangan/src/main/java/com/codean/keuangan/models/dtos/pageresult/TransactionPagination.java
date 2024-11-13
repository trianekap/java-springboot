package com.codean.keuangan.models.dtos.pageresult;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionPagination {
    private int id;
    private int user_id;
    private int account_id;
    private int category_id;
    private int transaction_type_id;
    private LocalDate date;
    private String description;
    private int amount;
}
