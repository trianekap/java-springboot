package com.codean.topup.models.dtos.resultmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PaketTopupForResult {
    private Long idPaket;
    private String namaPaket;
    private Long jumlahDiamond;
    private Long harga;
    private Long bonus;
}
