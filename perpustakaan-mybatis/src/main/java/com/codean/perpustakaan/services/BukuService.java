package com.codean.perpustakaan.services;

import com.codean.perpustakaan.models.dtos.BukuDTO;

import java.util.List;
import java.util.UUID;

public interface BukuService {

    //user
    BukuDTO findById(UUID id);
    List<BukuDTO> findAll();
    List<BukuDTO> findByJudul(String judul);
    List<BukuDTO> findByTahunTerbit(String tahunTerbit);

    //admin
    void update(BukuDTO bukuDTO);
    void delete(UUID id);
    void save(BukuDTO bukuDTO);
}
