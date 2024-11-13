package com.codean.perpustakaan.services.implement;


import com.codean.perpustakaan.mappers.BukuMapper;
import com.codean.perpustakaan.mappers.PinjamMapper;
import com.codean.perpustakaan.models.base.Buku;
import com.codean.perpustakaan.models.base.Pinjam;
import com.codean.perpustakaan.models.dtos.PinjamDTO;
import com.codean.perpustakaan.models.dtos.PinjamPostDTO;
import com.codean.perpustakaan.services.BukuService;
import com.codean.perpustakaan.services.PinjamService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PinjamServiceImpl implements PinjamService {

    private static final Logger log = LoggerFactory.getLogger(PinjamServiceImpl.class);
    private final PinjamMapper pinjamMapper;
    private final ModelMapper modelMapper;
    private final BukuService bukuService;
    private final BukuMapper bukuMapper;

    @Override
    public List<PinjamDTO> findAll(){
        return pinjamMapper.findAll().stream().map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PinjamDTO findById(UUID id){
        Pinjam pinjam = pinjamMapper.findById(id);
        return toDTO(pinjam);
    }

    @Override
    public void update(PinjamDTO pinjamDTO){

        Pinjam pinjam = pinjamMapper.findById(pinjamDTO.getIdPinjam());

        pinjam.setIdAnggota(pinjamDTO.getIdAnggota());
        pinjam.setIdBuku(pinjamDTO.getIdBuku());
        pinjam.setTanggalPinjam(pinjamDTO.getTanggalPinjam());
        pinjam.setTanggalKembali(pinjamDTO.getTanggalKembali());

        pinjamMapper.update(pinjam);

        toDTO(pinjam);
    }

    public void delete(UUID id){
        pinjamMapper.delete(id);
    }

    public void save(PinjamPostDTO pinjamPostDTO){
        Pinjam pinjam = toEntity(pinjamPostDTO);

        pinjam.setTanggalPinjam(LocalDateTime.now());

        Buku buku = bukuMapper.findById(pinjamPostDTO.getIdBuku())
                .orElseThrow(() -> new RuntimeException("buku doesnt exist"));

        if (Boolean.FALSE.equals(buku.getStatus())){
           log.warn("buku sedang dipinjam");
        }

        buku.setStatus(false);

        pinjamMapper.save(pinjam);

        bukuMapper.update(buku);
    }

//    public void restoreBuku(PinjamDTO pinjamDTO){
//        Pinjam pinjam = toEntity(pinjamDTO);
//
//        pinjam.setTanggalKembali(LocalDateTime.now());
//
//        Buku buku = bukuMapper.findById(pinjamDTO.getIdBuku())
//                .orElseThrow(() -> new RuntimeException("buku doesnt exist"));
//
//        pinjamMapper.
//    }

    private PinjamDTO toDTO(Pinjam pinjam){
        return modelMapper.map(pinjam, PinjamDTO.class);
    }

    private Pinjam toEntity(PinjamDTO pinjamDTO){
        return modelMapper.map(pinjamDTO, Pinjam.class);
    }

    private Pinjam toEntity(PinjamPostDTO pinjamPostDTO){
        return modelMapper.map(pinjamPostDTO, Pinjam.class);
    }

}
