package com.codean.perpustakaan.models.base;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class Buku {

    private UUID idBuku;
    private String judul;
    private String pengarang;
    private String tahunTerbit;
    private String jenisBuku;
    private Boolean status;
}
