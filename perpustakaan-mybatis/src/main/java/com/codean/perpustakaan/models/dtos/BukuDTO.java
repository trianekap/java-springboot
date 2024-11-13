package com.codean.perpustakaan.models.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class BukuDTO {

    private UUID idBuku;
    private String judul;
    private String pengarang;
    private String tahunTerbit;
    private String jenisBuku;
    private Boolean status;
}
