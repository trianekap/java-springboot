package com.codean.mybatispesantren.services.implementations;

import com.codean.mybatispesantren.mapper.KegiatanMapper;
import com.codean.mybatispesantren.model.post.Kegiatan;
import com.codean.mybatispesantren.services.KegiatanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KegiatanServiceImpl implements KegiatanService {

    @Autowired
    KegiatanMapper kegiatanMapper;

    @Override
    public List<Kegiatan> findAllKegiatan(){
        return kegiatanMapper.findAllKegiatan();
    }

    @Override
    public Kegiatan findKegiatanById(int idKegiatan){
        return kegiatanMapper.findKegiatan(idKegiatan);
    }

    @Override
    public void insertKegiatan(Kegiatan kegiatan){
        kegiatanMapper.insertKegiatan(kegiatan);
    }

    @Override
    public void updateKegiatan(int idKegiatan, Kegiatan kegiatan){
        kegiatanMapper.updateKegiatan(idKegiatan, kegiatan);
    }

    @Override
    public void deleteKegiatan(int idKegiatan){
        kegiatanMapper.deleteKegiatan(idKegiatan);
    }
}
