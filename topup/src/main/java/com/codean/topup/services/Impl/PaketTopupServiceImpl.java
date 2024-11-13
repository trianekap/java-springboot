package com.codean.topup.services.Impl;

import com.codean.topup.exceptions.ResourceAlreadyException;
import com.codean.topup.exceptions.ResourceNotExistException;
import com.codean.topup.mappers.PaketTopupMapper;
import com.codean.topup.models.dtos.PaketTopupDto;
import com.codean.topup.models.entity.PaketTopup;
import com.codean.topup.services.PaketTopupService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaketTopupServiceImpl implements PaketTopupService {

    @Autowired
    PaketTopupMapper paketTopupMapper;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public List<PaketTopupDto> getAll() throws ResourceNotExistException {
        List<PaketTopup> topupList = paketTopupMapper.getAll();

        if (topupList == null){
            throw new ResourceNotExistException("list is empty");
        }

        return topupList.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public PaketTopupDto getById(Long id) throws ResourceNotExistException{
        PaketTopup paketTopup = paketTopupMapper.getById(id);

        if (paketTopup == null){
            throw new ResourceNotExistException("that game with id " + id + " not found");
        }

        return toDto(paketTopup);
    }

    @Override
    public void create(PaketTopupDto paketTopupDto) {

        PaketTopup paketTopup = new PaketTopup();
        paketTopup.setNamaPaket(paketTopupDto.getNamaPaket());
        paketTopup.setIdGame(paketTopupDto.getIdGame());
        paketTopup.setHarga(paketTopupDto.getHarga());
        paketTopup.setJumlahDiamond(paketTopupDto.getJumlahDiamond());
        paketTopup.setBonus(paketTopupDto.getBonus());

        paketTopupMapper.create(paketTopup);
    }

    @Override
    public void update(Long id, PaketTopupDto paketTopupDto) throws ResourceNotExistException,
            ResourceAlreadyException {

        PaketTopup checkExist = paketTopupMapper.getById(id);

        if (checkExist == null){
            throw new ResourceNotExistException("that game with id " + id + " not found");
        }

        if (checkExist.getNamaPaket().equals(paketTopupDto.getNamaPaket())){
            throw new ResourceAlreadyException("that nama paket with " + paketTopupDto.getNamaPaket() + " aleady exist");
        }

        checkExist.setNamaPaket(paketTopupDto.getNamaPaket());
        checkExist.setIdGame(paketTopupDto.getIdGame());
        checkExist.setHarga(paketTopupDto.getHarga());
        checkExist.setJumlahDiamond(paketTopupDto.getJumlahDiamond());
        checkExist.setBonus(paketTopupDto.getBonus());

        paketTopupMapper.update(checkExist);
    }

    @Override
    public void delete(Long id) throws ResourceNotExistException {
        PaketTopup checkExist = paketTopupMapper.getById(id);

        if (checkExist == null){
            throw new ResourceNotExistException("that id with " + id + " not found");
        }

        paketTopupMapper.delete(id);
    }

    private PaketTopupDto toDto(PaketTopup paketTopup){
        return modelMapper.map(paketTopup, PaketTopupDto.class);
    }

    private PaketTopup toEntity(PaketTopupDto paketTopupDto){
        return modelMapper.map(paketTopupDto, PaketTopup.class);
    }
}
