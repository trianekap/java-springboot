package com.codean.mybatispesantren.mapper;

import com.codean.mybatispesantren.model.post.Kegiatan;
import com.codean.mybatispesantren.model.post.Santri;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface KegiatanMapper {

    List<Kegiatan> findAllKegiatan();

    Kegiatan findKegiatan(int idKegiatan);

    void insertKegiatan(Kegiatan kegiatan);

    void updateKegiatan(int idKegiatan, Kegiatan kegiatan);

    void deleteKegiatan(int idKegiatan);

    List<Santri> findSantriById(int idSantri);

    Kegiatan findDetailedKegiatan(int idKegiatan);
}