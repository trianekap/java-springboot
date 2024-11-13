package com.codean.perpustakaan.services;

import com.codean.perpustakaan.models.dtos.PinjamDTO;
import com.codean.perpustakaan.models.dtos.PinjamPostDTO;

import java.util.List;
import java.util.UUID;

public interface PinjamService {

    //user
    PinjamDTO findById(UUID id);
    List<PinjamDTO> findAll();

    //admin
    void update(PinjamDTO pinjamDTO);
    void delete(UUID id);
    void save(PinjamPostDTO pinjamPostDTO);
}
