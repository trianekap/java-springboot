package com.codean.mybatispesantren.model.entity;

import com.codean.mybatispesantren.model.dtos.kegiatan.KegiatanDTO;
import com.codean.mybatispesantren.model.dtos.santri.SantriDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Presensi {

    private int id;
    private String status;
    private int idSantri;
    private int idKegiatan;

    private List<SantriDTO> santri;
    private List<KegiatanDTO> kegiatan;

}
