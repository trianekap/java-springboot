package com.codean.topup.models.dtos.resultmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MethodBayarForResult {
    private Long idMethod;
    private String namaMethod;
    private Long biayaTransaksi;
}
