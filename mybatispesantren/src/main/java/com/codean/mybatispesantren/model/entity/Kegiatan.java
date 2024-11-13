package com.codean.mybatispesantren.model.entity;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Kegiatan {

    private int id;
    private String nama;
    private LocalDate tanggal;

    private List<Santri> listSantri;

}
