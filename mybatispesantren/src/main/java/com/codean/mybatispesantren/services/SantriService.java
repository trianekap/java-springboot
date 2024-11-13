package com.codean.mybatispesantren.services;

import com.codean.mybatispesantren.model.gets.SantriKehadiran;
import com.codean.mybatispesantren.model.post.Kegiatan;
import com.codean.mybatispesantren.model.post.Santri;

import java.util.List;

public interface SantriService {

    public List<Santri> findAllSantri();

    public Santri findSantri(int idSantri);

    public List<Santri> findByName(String name);

    public List<Santri> findByNameOrKelas(String name, String kelas);

    public List<Santri> findSantriKehadiran();

    public void insertSantri(Santri santri);

    public Santri findDetailedSantri(int idSantri);

    public void registerKegiatan(int idPeserta, Kegiatan kegiatan);

    public void updateSantri(int idSantri, Santri santri);

    public void deleteSantri(int idSantri);

}
