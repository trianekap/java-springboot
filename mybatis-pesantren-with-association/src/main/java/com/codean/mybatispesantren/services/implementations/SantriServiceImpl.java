package com.codean.mybatispesantren.services.implementations;

import com.codean.mybatispesantren.exceptions.ResourceNotExistException;
import com.codean.mybatispesantren.mapper.SantriMapper;
import com.codean.mybatispesantren.model.dtos.kegiatan.KegiatanDTO;
import com.codean.mybatispesantren.model.dtos.santri.SantriDTO;
import com.codean.mybatispesantren.model.dtos.santri.SantriGetDto;
import com.codean.mybatispesantren.model.dtos.santri.SantriUpdateDTO;
import com.codean.mybatispesantren.model.entity.Santri;
import com.codean.mybatispesantren.services.SantriService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SantriServiceImpl implements SantriService {


    private static final Logger log = LoggerFactory.getLogger(SantriServiceImpl.class);
    private final SantriMapper santriMapper;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<SantriGetDto> findAllSantri(){
        return santriMapper.findAllSantri().stream().map(this::toDTOGet)
                .collect(Collectors.toList());
    }

    @Override
    public SantriGetDto findSantri(int idSantri) throws ResourceNotExistException {
        Santri santri = santriMapper.findSantri(idSantri);
        if (santri == null){
            String message = "id tersebut tidak ada";
            log.error(message);
            throw new ResourceNotExistException(message);
        }
        return toDTOGet(santri);
    }

    @Override
    public void insertSantri(SantriGetDto santri){

        Santri santri1 = new Santri();
        santri1.setNama(santri.getNama());
        santri1.setUmur(santri.getUmur());
        santri1.setKelas(santri.getKelas());
        santri1.setUsername(santri.getUsername());
        santri1.setPassword(santri.getPassword());

        santri1.setPassword(passwordEncoder.encode(santri.getPassword()));

        santriMapper.insertSantri(toDTOGet(santri1));

    }

    @Override
    public SantriGetDto findDetailedSantri(int idSantri) throws ResourceNotExistException {
        Santri santri = santriMapper.findSantri(idSantri);
        if (santri == null){
            String message = "id tersebut tidak ada";
            log.error(message);
            throw new ResourceNotExistException(message);
        }
        return toDTOGet(santri);
    }

    @Override
    public List<SantriGetDto> findByName(String name){
        List<Santri> santris = santriMapper.findByName(name);
        return santris.stream().map(this::toDTOGet).collect(Collectors.toList());
    }

    @Override
    public List<SantriGetDto> findByNameOrKelas(String nama, String kelas){
        List<Santri> santris = santriMapper.findByNameOrKelas(nama, kelas);
        return santris.stream().map(this::toDTOGet).collect(Collectors.toList());
    }

    @Override
    public List<SantriGetDto> findSantriKehadiran(){
        return santriMapper.findSantriKehadiran().stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<SantriGetDto> findByAge(int umur) {
        List<Santri> santris = santriMapper.findByAge(umur);
        return santris.stream().map(this::toDTOGet)
                .collect(Collectors.toList());
    }

    @Override
    public void registerKegiatan(int idSantri, KegiatanDTO kegiatan) throws ResourceNotExistException {
        SantriGetDto santriRegisterDTO = findSantri(idSantri);
        if (santriRegisterDTO == null){
            String message = "id tersebut tidak ada";
            log.error(message);
            throw new ResourceNotExistException(message);
        }
        updateSantri(idSantri, toUpdateDTO(santriRegisterDTO));
    }



    @Override
    public void updateSantri(int id, SantriUpdateDTO santriUpdateDTO) throws ResourceNotExistException {
        Santri santri1 = santriMapper.findSantri(id);
        if (santri1 == null){
            String message = "id tersebut tidak ada";
            log.error(message);
            throw new ResourceNotExistException(message);
        }

        santri1.setNama(santriUpdateDTO.getNama());
        santri1.setUmur(santriUpdateDTO.getUmur());
        santri1.setKelas(santriUpdateDTO.getKelas());

        santriMapper.updateSantri(santri1);
    }


    @Override
    public void deleteSantri(int idSantri) throws ResourceNotExistException {
        Santri santri = santriMapper.findSantri(idSantri);
        if (santri == null){
            String message = "id tersebut tidak ada";
            log.error(message);
            throw new ResourceNotExistException(message);
        }
        santriMapper.deleteSantri(idSantri);
    }

    private SantriDTO toDTO(Santri santri){
        return modelMapper.map(santri, SantriDTO.class);
    }

    private SantriGetDto toDTOGet(Santri santri){
        return modelMapper.map(santri, SantriGetDto.class);
    }

    private Santri toEntity(SantriDTO santriDTO){
        return modelMapper.map(santriDTO, Santri.class);
    }

    private SantriUpdateDTO toUpdateDTO(SantriGetDto santriGetDto){
        return modelMapper.map(santriGetDto, SantriUpdateDTO.class);
    }


}
