package com.codean.mybatispesantren.model.dtos.presensi;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
//@NoArgsConstructor
public class GetAllPresensi {

    private int id;
    private int id_santri;
    private int id_kegiatan;
    private String status;
    private String nama_santri;
    private int umur_santri;
    private String nama_kegiatan;
    private LocalDate tanggal_kegiatan;
}
