package com.codean.mybatispesantren.model.gets;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SantriKehadiran {

    private int santriId;
    private String santriNama;
    private int umur;
    private String kelas;
    private String kegiatanNama;
    private Date tanggal;
    private String status;
}
