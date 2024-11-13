package com.codean.perpustakaan.services.implement;

import com.codean.perpustakaan.mappers.BukuMapper;
import com.codean.perpustakaan.models.base.Buku;
import com.codean.perpustakaan.models.dtos.BukuDTO;
import com.codean.perpustakaan.services.BukuService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BukuServiceImpl implements BukuService {

    private static final Logger log = LoggerFactory.getLogger(BukuServiceImpl.class);
    private final BukuMapper bukuMapper;
    private final ModelMapper modelMapper;

    @Override
    public List<BukuDTO> findAll(){
        return bukuMapper.findAll().stream().map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BukuDTO findById(UUID id){
        Buku buku = bukuMapper.findById(id)
                .orElseThrow(() -> new RuntimeException("buku doesnt exist"));
        return toDTO(buku);
    }

    @Override
    public List<BukuDTO> findByJudul(String judul){
        List<Buku> bukuList = bukuMapper.findByJudul(judul);
        return bukuList.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BukuDTO> findByTahunTerbit(String tahunTerbit){
        List<Buku> bukuList = bukuMapper.findByTahunTerbit(tahunTerbit);
        return bukuList.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void update(BukuDTO bukuDTO){

        Buku buku = bukuMapper.findById(bukuDTO.getIdBuku())
                .orElseThrow(() -> new RuntimeException("buku doesnt exist"));

        buku.setJudul(bukuDTO.getJudul());
        buku.setPengarang(bukuDTO.getPengarang());
        buku.setTahunTerbit(bukuDTO.getTahunTerbit());
        buku.setJenisBuku(bukuDTO.getJenisBuku());
        buku.setStatus(bukuDTO.getStatus());

        bukuMapper.update(buku);

        toDTO(buku);
    }

    public void delete(UUID id){
        bukuMapper.delete(id);
    }

    public void save(BukuDTO bukuDTO){
        Buku buku = toEntity(bukuDTO);
        bukuMapper.save(buku);
    }

    private BukuDTO toDTO(Buku buku){
        return modelMapper.map(buku, BukuDTO.class);
    }

    private Buku toEntity(BukuDTO bukuDTO){
        return modelMapper.map(bukuDTO, Buku.class);
    }
}
