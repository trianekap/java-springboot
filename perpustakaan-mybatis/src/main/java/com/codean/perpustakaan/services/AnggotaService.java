package com.codean.perpustakaan.services;

import com.codean.perpustakaan.models.dtos.AnggotaDTO;
import java.util.List;
import java.util.UUID;

public interface AnggotaService {
    //user
    AnggotaDTO findById(UUID id);
    List<AnggotaDTO> findAll();
    List<AnggotaDTO> findByName(String name);

    //admin
    void update(AnggotaDTO anggotaDTO);
    void delete(UUID id);
    void save(AnggotaDTO anggotaDTO);
}
