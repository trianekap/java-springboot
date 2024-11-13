package com.codean.perpustakaan.models.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PinjamDTO {

    private UUID idPinjam;
    private UUID idAnggota;
    private UUID idBuku;
    private LocalDateTime tanggalPinjam;
    private LocalDateTime tanggalKembali;
}
