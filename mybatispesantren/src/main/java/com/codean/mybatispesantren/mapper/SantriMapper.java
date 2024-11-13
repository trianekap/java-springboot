package com.codean.mybatispesantren.mapper;

import com.codean.mybatispesantren.model.post.Santri;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SantriMapper {

    List<Santri> findAllSantri();

    Santri findSantri(int idSantri);

    List<Santri> findByName(String santri);
    List<Santri> findByNameOrKelas(String nama, String kelas);
    List<Santri> findSantriKehadiran();

    void insertSantri(Santri santri);

    void updateSantri(int idSantri, Santri santri);

    void deleteSantri(int idSantri);

    List<Santri> findByKegiatanId(int idKegiatan);

    Santri findDetailedSantri(int idSantri);



}
