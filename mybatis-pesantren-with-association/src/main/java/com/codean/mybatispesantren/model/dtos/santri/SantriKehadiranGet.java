package com.codean.mybatispesantren.model.dtos.santri;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SantriKehadiranGet {

    private int santriId;
    private String santriNama;
    private int umur;
    private String kelas;
    private String kegiatanNama;
    private LocalDate tanggal;
    private String status;
}
