package com.codean.perpustakaan.services.implement;

import com.codean.perpustakaan.mappers.AnggotaMapper;
import com.codean.perpustakaan.models.base.Anggota;
import com.codean.perpustakaan.models.dtos.AnggotaDTO;
import com.codean.perpustakaan.services.AnggotaService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnggotaServiceImpl implements AnggotaService {

    private static final Logger log = LoggerFactory.getLogger(AnggotaServiceImpl.class);
    private final AnggotaMapper anggotaMapper;
    private final ModelMapper modelMapper;

    @Override
    public List<AnggotaDTO> findAll(){
        return anggotaMapper.findAll().stream().map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AnggotaDTO findById(UUID id){
        Anggota anggota = anggotaMapper.findById(id);
        return toDTO(anggota);
    }

    @Override
    public List<AnggotaDTO> findByName(String name){
        List<Anggota> anggotaList = anggotaMapper.findByName(name);
        return anggotaList.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void update(AnggotaDTO anggotaDTO){

        Anggota anggota = anggotaMapper.findById(anggotaDTO.getIdAnggota());

        if (anggota == null) {
            log.error("ID anggota tidak ada: {}", anggotaDTO.getIdAnggota());
        }

        anggota.setNama(anggotaDTO.getNama());
        anggota.setAlamat(anggotaDTO.getAlamat());
        anggota.setKota(anggotaDTO.getKota());
        anggota.setNoTelp(anggotaDTO.getNoTelp());
        anggota.setTanggalLahir(anggotaDTO.getTanggalLahir());

        anggotaMapper.update(anggota);

        toDTO(anggota);
    }

    public void delete(UUID id){
        anggotaMapper.delete(id);
    }

    public void save(AnggotaDTO anggotaDTO){
        Anggota anggota = toEntity(anggotaDTO);
        anggotaMapper.save(anggota);
    }

    private AnggotaDTO toDTO(Anggota anggota){
        return modelMapper.map(anggota, AnggotaDTO.class);
    }

    private Anggota toEntity(AnggotaDTO anggotaDTO){
        return modelMapper.map(anggotaDTO, Anggota.class);
    }


}
