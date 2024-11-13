package com.codean.mybatispesantren.mapper;

import com.codean.mybatispesantren.model.dtos.santri.SantriDTO;
import com.codean.mybatispesantren.model.dtos.santri.SantriGetDto;
import com.codean.mybatispesantren.model.entity.Santri;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SantriMapper {

    List<Santri> findAllSantri();

    Santri findSantri(int idSantri);

    List<Santri> findByName(String santri);

    Santri findByUsername(String username);

    List<Santri> findByNameOrKelas(String nama, String kelas);
    List<SantriGetDto> findSantriKehadiran();
    List<Santri> findByAge(int umur);

    void insertSantri(SantriGetDto santri);

    void updateSantri(Santri santri);

    void deleteSantri(int idSantri);

    List<Santri> findByKegiatanId(int idKegiatan);

    SantriDTO findDetailedSantri(int idSantri);



}
