package com.codean.topup.services.Impl;

import com.codean.topup.exceptions.ResourceAlreadyException;
import com.codean.topup.exceptions.ResourceNotExistException;
import com.codean.topup.mappers.MataUangMapper;
import com.codean.topup.models.dtos.MataUangDto;
import com.codean.topup.models.entity.Game;
import com.codean.topup.models.entity.MataUang;
import com.codean.topup.services.MataUangService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MataUangServiceImpl implements MataUangService {

    @Autowired
    MataUangMapper mataUangMapper;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public List<MataUangDto> getAll() throws ResourceNotExistException {
        List<MataUang> mataUangList = mataUangMapper.getAll();

        if (mataUangList == null){
            throw new ResourceNotExistException("list is empty");
        }

        return mataUangList.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public MataUangDto getById(Long id) throws ResourceNotExistException {
        MataUang mataUang = mataUangMapper.getById(id);

        if (mataUang == null){
            throw new ResourceNotExistException("that game with id " + id + " not found");
        }

        return toDto(mataUang);
    }

    @Override
    public void create(MataUangDto mataUangDto) {
        MataUang mataUang1 = new MataUang();

        mataUang1.setNamaMataUang(mataUangDto.getNamaMataUang());

        mataUangMapper.create(mataUang1);
    }

    @Override
    public void update(Long id, MataUangDto mataUangDto) throws ResourceNotExistException, ResourceAlreadyException {
        MataUang checkExist = mataUangMapper.getById(id);

        if (checkExist == null){
            throw new ResourceNotExistException("that mata uang with id " + id + " not found");
        }

        if (checkExist.getNamaMataUang().equals(mataUangDto.getNamaMataUang())){
            throw new ResourceAlreadyException("that mata uang with name " + mataUangDto.getNamaMataUang() + " already exist");
        }

        checkExist.setNamaMataUang(mataUangDto.getNamaMataUang());

        mataUangMapper.update(checkExist);
    }

    @Override
    public void delete(Long id) throws ResourceNotExistException {
        MataUang checkExist = mataUangMapper.getById(id);

        if (checkExist == null){
            throw new ResourceNotExistException("that id with " + id + " not found");
        }

        mataUangMapper.delete(id);
    }

    private MataUangDto toDto(MataUang mataUang){
        return modelMapper.map(mataUang, MataUangDto.class);
    }

    private MataUang toEntity(MataUangDto mataUangDto){
        return modelMapper.map(mataUangDto, MataUang.class);
    }
}
