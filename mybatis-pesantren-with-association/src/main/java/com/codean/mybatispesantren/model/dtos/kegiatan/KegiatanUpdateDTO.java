package com.codean.mybatispesantren.model.dtos.kegiatan;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KegiatanUpdateDTO {


    private String nama;
    private LocalDate tanggal;
}
