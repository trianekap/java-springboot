package com.codean.mybatispesantren.services;

import com.codean.mybatispesantren.exceptions.ResourceNotExistException;
import com.codean.mybatispesantren.model.dtos.kegiatan.KegiatanDTO;
import com.codean.mybatispesantren.model.dtos.kegiatan.KegiatanGetAllDTO;
import com.codean.mybatispesantren.model.dtos.kegiatan.KegiatanUpdateDTO;
import com.codean.mybatispesantren.model.entity.Kegiatan;

import java.util.Date;
import java.util.List;

public interface KegiatanService {

    List<Kegiatan> findAllKegiatan(int id);
    List<KegiatanGetAllDTO> getAll();
    List<KegiatanGetAllDTO> findByName(String nama);
    List<KegiatanDTO> findByDate(Date startDate, Date endDate) throws ResourceNotExistException;
    List<KegiatanDTO> findDetailKegiatan(int idKegiatan);


    Kegiatan findKegiatanById(int idKegiatan) throws ResourceNotExistException;
    void insertKegiatan(KegiatanDTO kegiatan);

    void updateKegiatan(int id, Kegiatan kegiatan) throws ResourceNotExistException;

    void deleteKegiatan(int idKegiatan);
}
