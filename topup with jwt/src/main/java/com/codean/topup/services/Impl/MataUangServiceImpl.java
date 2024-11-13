package com.codean.topup.services.Impl;

import com.codean.topup.exceptions.ResourceAlreadyException;
import com.codean.topup.exceptions.ResourceNotExistException;
import com.codean.topup.mappers.MataUangMapper;
import com.codean.topup.models.dtos.MataUangDto;
import com.codean.topup.models.dtos.UserDto;
import com.codean.topup.models.dtos.pageresult.PageResult;
import com.codean.topup.models.entity.MataUang;
import com.codean.topup.services.MataUangService;
import com.codean.topup.util.PaginationHelper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MataUangServiceImpl implements MataUangService {

    @Autowired
    MataUangMapper mataUangMapper;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public PageResult<MataUangDto> getAllByPage(int pageNumber, int pageSize) throws ResourceNotExistException {
        if (pageNumber < 1) pageNumber = 1;
        if (pageSize < 1) pageSize = 10;

        int offset = (pageNumber - 1) * pageSize;

        Map<String, Object> params = new HashMap<>();
        params.put("offset", offset);
        params.put("limit", pageSize);
        List<MataUangDto> mataUangDtoList = mataUangMapper.getAllByPage(params);

        Long totalElement = mataUangMapper.countMataUang();

        int totalPage = PaginationHelper.calculateTotalPages(totalElement, pageSize);

        boolean isLastPage;
        isLastPage = pageNumber >= totalPage;

        PageResult<MataUangDto> pageResult = new PageResult<>();
        pageResult.setContent(mataUangDtoList);
        pageResult.setPage_number(pageNumber);
        pageResult.setPage_size(pageSize);
        pageResult.setTotal_element(totalElement);
        pageResult.setTotal_page(totalPage);
        pageResult.setLast_page(isLastPage);

        return pageResult;
    }

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
