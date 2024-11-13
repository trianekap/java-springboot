package com.codean.mybatispesantren.model.dtos.presensi;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PresensiDTO {
    private int id;
    private String status;
    private String nama_santri;
    private LocalDate tanggal;
    private String nama_kegiatan;
}
