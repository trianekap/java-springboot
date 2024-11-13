package com.codean.mybatispesantren.services;

import com.codean.mybatispesantren.model.post.Kegiatan;

import java.util.List;

public interface KegiatanService {

    public List<Kegiatan> findAllKegiatan();
    public Kegiatan findKegiatanById(int idKegiatan);
    public void insertKegiatan(Kegiatan kegiatan);
    public void updateKegiatan(int idKegiatan, Kegiatan kegiatan);
    public void deleteKegiatan(int idKegiatan);
}
