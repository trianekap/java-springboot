package com.codean.mybatispesantren.mapper;

import com.codean.mybatispesantren.model.dtos.kegiatan.KegiatanDTO;
import com.codean.mybatispesantren.model.dtos.kegiatan.KegiatanGetAllDTO;
import com.codean.mybatispesantren.model.entity.Kegiatan;
import com.codean.mybatispesantren.model.entity.Santri;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface KegiatanMapper {

    List<Kegiatan> findAllKegiatan(int id);

    List<KegiatanGetAllDTO> getAll();

    List<KegiatanGetAllDTO> findByName(String nama);

    List<Kegiatan> findByDate(Date startDate, Date endDate);

    List<Kegiatan> findDetailKegiatan(int idKegiatan);

    Kegiatan findKegiatan(int idKegiatan);

    void insertKegiatan(Kegiatan kegiatan);

    void updateKegiatan(Kegiatan kegiatan);

    void deleteKegiatan(int idKegiatan);

    List<Santri> findSantriById(int idSantri);

//    Kegiatan findDetailedKegiatan(int idKegiatan);
}