package com.codean.topup.models.dtos.resultmodel;

import com.codean.topup.models.dtos.MethodBayarDto;
import com.codean.topup.models.dtos.PaketTopupDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameDtoList {

    private String namaGame;
    private String mataUang;
    private String jenisGame;

    private List<PaketTopupForResult> paketTopup;
    private List<MethodBayarForResult> metodePembayaran;
}
