package com.codean.topup.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MethodBayarDto {
    private Long idMethod;
    @NotBlank(message = "nama methode cannot be null!")
    private String namaMethod;
    @NotNull(message = "biaya transaksi cannot be null!")
    @Min(value = 100)
    private Long biayaTransaksi;
    @NotNull(message = "id mata uang cannot be null")
    private Long idMataUang;
}
