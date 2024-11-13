package com.codean.mybatispesantren.services.implementations;

import com.codean.mybatispesantren.exceptions.ResourceNotExistException;
import com.codean.mybatispesantren.mapper.KegiatanMapper;
import com.codean.mybatispesantren.model.dtos.kegiatan.KegiatanDTO;
import com.codean.mybatispesantren.model.dtos.kegiatan.KegiatanGetAllDTO;
import com.codean.mybatispesantren.model.entity.Kegiatan;
import com.codean.mybatispesantren.services.KegiatanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class KegiatanServiceImpl implements KegiatanService {

    private static final Logger log = LoggerFactory.getLogger(KegiatanServiceImpl.class);
    @Autowired
    KegiatanMapper kegiatanMapper;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<Kegiatan> findAllKegiatan(int id){
        //panggil untuk set
//        List<Kegiatan> kegiatanList = kegiatanMapper.findAllKegiatan(id);
//        List<KegiatanDTO> kegiatanDTOList = new ArrayList<>();
//        for (Kegiatan kegiatan : kegiatanList){
//            KegiatanDTO kegiatanDTO = new KegiatanDTO();
//            kegiatanDTO.setId(kegiatan.getId());
//            kegiatanDTO.setNama(kegiatan.getNama());
//            kegiatanDTO.setTanggal(kegiatan.getTanggal());
//            kegiatanDTO.setSantriDTO(new ArrayList<>());
//            kegiatanDTOList.add(kegiatanDTO);
//        }
//
//        Map<Integer, KegiatanDTO> kegiatanDTOMap = kegiatanDTOList.stream()
//                .collect(Collectors.toMap(KegiatanDTO::getId, Function.identity()));
//
//        return kegiatanList;
        return kegiatanMapper.findAllKegiatan(id);

     }

    @Override
    public List<KegiatanGetAllDTO> getAll() {
        return kegiatanMapper.getAll().stream()
                .collect(Collectors.toList());
    }


    @Override
    public Kegiatan findKegiatanById(int idKegiatan) throws ResourceNotExistException {
        Kegiatan kegiatan = kegiatanMapper.findKegiatan(idKegiatan);
        if (kegiatan == null){
            String message = "id tersebut tidak ada";
            log.error(message);
            throw new ResourceNotExistException(message);
        }

        return kegiatan;
    }

    @Override
    public List<KegiatanDTO> findDetailKegiatan(@PathVariable int idKegiatan){
        List<Kegiatan> kegiatanDTOList = kegiatanMapper.findDetailKegiatan(idKegiatan);
        return kegiatanDTOList.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<KegiatanGetAllDTO> findByName(String nama){
        List<KegiatanGetAllDTO> kegiatanList = kegiatanMapper.findByName(nama);
        return kegiatanList.stream().collect(Collectors.toList());
    }

    @Override
    public List<KegiatanDTO> findByDate(@RequestParam Date startDate, @RequestParam Date endDate) throws ResourceNotExistException{
        List<Kegiatan> kegiatanList = kegiatanMapper.findByDate(startDate, endDate);

        if (kegiatanList.isEmpty()) {
           String message = "data nya kosong";
           log.error(message);
           throw new ResourceNotExistException(message);
        }

        return kegiatanList.stream().map(this::toDTO).collect(Collectors.toList());

    }

    @Override
    public void insertKegiatan(KegiatanDTO kegiatan){
        kegiatanMapper.insertKegiatan(toEntity(kegiatan));
    }

    @Override
    public void updateKegiatan(int id, Kegiatan kegiatan) throws ResourceNotExistException {

        Kegiatan kegiatan1 = kegiatanMapper.findKegiatan(id);
        if (kegiatan == null){
            String message = "kegiatan not found";
            log.warn(message);
            throw new ResourceNotExistException(message);
        }

        kegiatan1.setNama(kegiatan.getNama());
        kegiatan1.setTanggal(kegiatan.getTanggal());

        kegiatanMapper.updateKegiatan(kegiatan1);
    }


    @Override
    public void deleteKegiatan(int idKegiatan){
        kegiatanMapper.deleteKegiatan(idKegiatan);
    }

    private KegiatanDTO toDTO(Kegiatan kegiatan){
        return modelMapper.map(kegiatan, KegiatanDTO.class);
    }

    private KegiatanGetAllDTO toGetAllDTO(Kegiatan kegiatan){
        return modelMapper.map(kegiatan, KegiatanGetAllDTO.class);
    }



    private Kegiatan toEntity(KegiatanDTO kegiatanDTO){
        return modelMapper.map(kegiatanDTO, Kegiatan.class);
    }

    private Kegiatan toEntityGetAll(KegiatanGetAllDTO kegiatanGetAllDTO){
        return modelMapper.map(kegiatanGetAllDTO, Kegiatan.class);
    }
}
