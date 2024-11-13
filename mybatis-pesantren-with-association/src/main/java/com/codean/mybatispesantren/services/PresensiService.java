package com.codean.mybatispesantren.services;

import com.codean.mybatispesantren.model.dtos.presensi.PresensiDTO;
import com.codean.mybatispesantren.model.entity.Presensi;

import java.util.List;

public interface PresensiService {

    void register(int idSantri, int idKegiatan, String status);
    void cancelRegister(int idSantri, int idKegiatan, String status);
    List<PresensiDTO> searchPresensi(String keyword);
    List<Presensi> findAllKegiatanByPresensi();
    List<Presensi> findAllPresensi();
}
