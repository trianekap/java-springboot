package com.codean.mybatispesantren.model.dtos.kegiatan;

import com.codean.mybatispesantren.model.dtos.santri.SantriDTO;
import com.codean.mybatispesantren.model.entity.Presensi;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KegiatanDTO {
    private int id;
    private String nama;
    private LocalDate tanggal;

    private List<SantriDTO> santriDTO;
    private List<Presensi> presensiList;
}
