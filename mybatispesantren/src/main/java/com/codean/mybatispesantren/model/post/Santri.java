package com.codean.mybatispesantren.model.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Santri {

    private int id;
    private String nama;
    private int umur;
    private String kelas;

    private List<Kegiatan> listKegiatan = new ArrayList<>();
}
