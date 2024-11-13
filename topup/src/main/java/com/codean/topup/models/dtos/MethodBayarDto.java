package com.codean.topup.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MethodBayarDto {
    private Long idMethod;
    private String namaMethod;
    private Long biayaTransaksi;
    private Long idMataUang;
}
