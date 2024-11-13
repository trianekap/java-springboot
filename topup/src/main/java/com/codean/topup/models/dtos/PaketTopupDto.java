package com.codean.topup.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaketTopupDto {
    private Long idPaket;
    private String namaPaket;
    private Long idGame;
    private Long jumlahDiamond;
    private Long harga;
    private Long bonus;
}
