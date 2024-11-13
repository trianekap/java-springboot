package com.codean.mybatispesantren.services.implementations;

import com.codean.mybatispesantren.mapper.SantriMapper;
import com.codean.mybatispesantren.model.gets.SantriKehadiran;
import com.codean.mybatispesantren.model.post.Kegiatan;
import com.codean.mybatispesantren.model.post.Santri;
import com.codean.mybatispesantren.services.SantriService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SantriServiceImpl implements SantriService {


    private final SantriMapper santriMapper;

    @Override
    public List<Santri> findAllSantri(){
        return santriMapper.findAllSantri();
    }

    @Override
    public Santri findSantri(int idSantri){
        return santriMapper.findSantri(idSantri);
    }

    @Override
    public void insertSantri(Santri santri){
        santriMapper.insertSantri(santri);
    }

    @Override
    public Santri findDetailedSantri(int idSantri){
        return santriMapper.findDetailedSantri(idSantri);
    }

    @Override
    public List<Santri> findByName(String name){
        List<Santri> santris = santriMapper.findByName(name);
        return santris.stream().collect(Collectors.toList());
    }

    @Override
    public List<Santri> findByNameOrKelas(String name, String kelas){
        List<Santri> santris = santriMapper.findByNameOrKelas(name, kelas);
        return santris.stream().collect(Collectors.toList());
    }

    @Override
    public List<Santri> findSantriKehadiran(){
        return santriMapper.findSantriKehadiran();
    }

    @Override
    public void registerKegiatan(int idSantri, Kegiatan kegiatan){
        Santri santri = findSantri(idSantri);
        santri.getListKegiatan().add(kegiatan);
        updateSantri(idSantri, santri);
    }

    @Override
    public void updateSantri(int idSantri, Santri santri){
        santriMapper.updateSantri(idSantri, santri);
    }

    @Override
    public void deleteSantri(int idSantri){
        santriMapper.deleteSantri(idSantri);
    }

}
