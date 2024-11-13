package com.codean.perpustakaan.models.dtos;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PinjamPostDTO {

    private UUID idPinjam;
    private UUID idAnggota;
    private UUID idBuku;
    private LocalDateTime tanggalPinjam;
}
