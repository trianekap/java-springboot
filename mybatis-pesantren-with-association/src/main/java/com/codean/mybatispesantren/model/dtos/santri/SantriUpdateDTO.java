package com.codean.mybatispesantren.model.dtos.santri;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SantriUpdateDTO {
        private int id;
        private String nama;
        private int umur;
        private String kelas;
}
