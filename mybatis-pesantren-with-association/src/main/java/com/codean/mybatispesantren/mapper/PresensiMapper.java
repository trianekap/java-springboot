package com.codean.mybatispesantren.mapper;

import com.codean.mybatispesantren.model.dtos.presensi.PresensiDTO;
import com.codean.mybatispesantren.model.entity.Presensi;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PresensiMapper {

    void insert(int idSantri, int idKegiatan, String status);
    void delete(int idSantri, int idKegiatan, String status);
    List<PresensiDTO> searchPresensi(String keyword);
    List<Presensi> findAllKegiatanByPresensi();
    List<Presensi> findAllPresensi();
}
