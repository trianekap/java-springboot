package com.codean.mybatispesantren.services.implementations;

import com.codean.mybatispesantren.mapper.PresensiMapper;
import com.codean.mybatispesantren.services.PresensiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PresensiServiceImpl implements PresensiService {

    @Autowired
    PresensiMapper presensiMapper;

    @Override
    public void register(int idSantri, int idKegiatan, String status){
        presensiMapper.insert(idSantri, idKegiatan, status);
    }

    @Override
    public void cancelRegister(int idSantri, int idKegiatan, String status){
        presensiMapper.delete(idSantri, idKegiatan, status);
    }
}
