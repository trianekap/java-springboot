package com.codean.mybatispesantren.model;

import com.codean.mybatispesantren.model.gets.KegiatanGet;
import com.codean.mybatispesantren.model.gets.SantriGet;
import com.codean.mybatispesantren.model.post.Kegiatan;
import com.codean.mybatispesantren.model.post.Santri;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Presensi {

    private int id;
    private String status;

}
