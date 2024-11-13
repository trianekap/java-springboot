package com.codean.mybatispesantren.services;

import com.codean.mybatispesantren.exceptions.ResourceNotExistException;
import com.codean.mybatispesantren.model.dtos.kegiatan.KegiatanDTO;
import com.codean.mybatispesantren.model.dtos.santri.SantriGetDto;
import com.codean.mybatispesantren.model.dtos.santri.SantriUpdateDTO;

import java.util.List;

public interface SantriService {

    List<SantriGetDto> findAllSantri();

    SantriGetDto findSantri(int idSantri) throws ResourceNotExistException;

    List<SantriGetDto> findByName(String name);

    List<SantriGetDto> findByNameOrKelas(String name, String kelas);

    List<SantriGetDto> findSantriKehadiran();

    void insertSantri(SantriGetDto santri);

    SantriGetDto findDetailedSantri(int idSantri) throws ResourceNotExistException;

    List<SantriGetDto> findByAge(int umur);

    void registerKegiatan(int idPeserta, KegiatanDTO kegiatan) throws ResourceNotExistException;

    void updateSantri(int id, SantriUpdateDTO santriUpdateDTO) throws ResourceNotExistException;

    void deleteSantri(int idSantri) throws ResourceNotExistException;

}
