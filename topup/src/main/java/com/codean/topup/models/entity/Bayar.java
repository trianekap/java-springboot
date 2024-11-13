package com.codean.topup.models.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bayar {
    private Long idBayar;
    private Long idPaket;
    private Long idMethod;
}
