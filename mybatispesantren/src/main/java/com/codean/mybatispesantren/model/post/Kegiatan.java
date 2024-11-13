package com.codean.mybatispesantren.model.post;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Kegiatan {

    private int id;
    private String nama;
    private Date tanggal;

    private List<Santri> listSantri = new ArrayList<>();

}
