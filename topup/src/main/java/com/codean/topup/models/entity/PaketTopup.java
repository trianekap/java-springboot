package com.codean.topup.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaketTopup {
    private Long idPaket;
    private String namaPaket;
    private Long idGame;
    private Long jumlahDiamond;
    private Long harga;
    private Long bonus;
}
