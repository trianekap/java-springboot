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
public class KegiatanGet {
    private String nama;
    private Date tanggal;
}
