package com.codean.perpustakaan.models.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnggotaDTO {

    private UUID idAnggota;
    private String nama;
    private String alamat;
    private String kota;
    private Integer noTelp;
    private LocalDate tanggalLahir;
}
