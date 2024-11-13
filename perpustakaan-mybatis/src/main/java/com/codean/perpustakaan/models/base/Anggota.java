package com.codean.perpustakaan.models.base;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Anggota {

    private UUID idAnggota;
    private String nama;
    private String alamat;
    private String kota;
    private Integer noTelp;
    private LocalDate tanggalLahir;
}
