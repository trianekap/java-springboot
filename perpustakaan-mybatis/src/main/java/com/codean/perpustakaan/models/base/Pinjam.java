package com.codean.perpustakaan.models.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pinjam {

    private UUID idPinjam;
    private UUID idAnggota;
    private UUID idBuku;
    private LocalDateTime tanggalPinjam;
    private LocalDateTime tanggalKembali;
}
