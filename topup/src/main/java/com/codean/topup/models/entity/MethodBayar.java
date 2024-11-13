package com.codean.topup.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MethodBayar {
    private Long idMethod;
    private String namaMethod;
    private Long biayaTransaksi;
    private Long idMataUang;
}
