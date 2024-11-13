package com.codean.keuangan.models.dtos.resultmodel;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionFilter {
    private String username;
    private LocalDate date;
    private String description;
    private int amount;
    private String name;
}
