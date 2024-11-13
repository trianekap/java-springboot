package com.codean.mybatispesantren.services.implementations;

import com.codean.mybatispesantren.mapper.PresensiMapper;
import com.codean.mybatispesantren.model.dtos.presensi.PresensiDTO;
import com.codean.mybatispesantren.model.entity.Presensi;
import com.codean.mybatispesantren.services.PresensiService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PresensiServiceImpl implements PresensiService {

    @Autowired
    PresensiMapper presensiMapper;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public void register(int idSantri, int idKegiatan, String status){
        presensiMapper.insert(idSantri, idKegiatan, status);
    }

    @Override
    public void cancelRegister(int idSantri, int idKegiatan, String status){
        presensiMapper.delete(idSantri, idKegiatan, status);
    }

    @Override
    public List<PresensiDTO> searchPresensi(String key) {

        return presensiMapper.searchPresensi(key).stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<Presensi> findAllPresensi(){
        return presensiMapper.findAllPresensi();
    }

    @Override
    public List<Presensi> findAllKegiatanByPresensi() {

        return new ArrayList<>(presensiMapper.findAllKegiatanByPresensi());
    }

    private PresensiDTO toDto(Presensi presensi){
        PresensiDTO presensiDTO = new PresensiDTO();
        presensiDTO.setId(presensi.getId());
        presensiDTO.setStatus(presensi.getStatus());
        return presensiDTO;
    }

    private Presensi toEntity(PresensiDTO presensiDTO){
        return modelMapper.map(presensiDTO, Presensi.class);
    }


}
