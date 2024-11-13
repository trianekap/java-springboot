package com.codean.topup.services.Impl;

import com.codean.topup.exceptions.ResourceAlreadyException;
import com.codean.topup.exceptions.ResourceNotExistException;
import com.codean.topup.mappers.MethodBayarMapper;
import com.codean.topup.models.dtos.MethodBayarDto;
import com.codean.topup.models.dtos.UserDto;
import com.codean.topup.models.dtos.pageresult.PageResult;
import com.codean.topup.models.entity.MethodBayar;
import com.codean.topup.services.MethodBayarService;
import com.codean.topup.util.PaginationHelper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MethodBayarServiceImpl implements MethodBayarService {

    @Autowired
    MethodBayarMapper methodBayarMapper;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public PageResult<MethodBayarDto> getAllByPage(int pageNumber, int pageSize) throws ResourceNotExistException {
        if (pageNumber < 1) pageNumber = 1;
        if (pageSize < 1) pageSize = 10;

        int offset = (pageNumber - 1) * pageSize;

        Map<String, Object> params = new HashMap<>();
        params.put("offset", offset);
        params.put("limit", pageSize);
        List<MethodBayarDto> methodBayarDtos = methodBayarMapper.getAllByPage(params);

        Long totalElement = methodBayarMapper.countMethodBayar();

        int totalPage = PaginationHelper.calculateTotalPages(totalElement, pageSize);

        boolean isLastPage;
        isLastPage = pageNumber >= totalPage;

        PageResult<MethodBayarDto> pageResult = new PageResult<>();
        pageResult.setContent(methodBayarDtos);
        pageResult.setPage_number(pageNumber);
        pageResult.setPage_size(pageSize);
        pageResult.setTotal_element(totalElement);
        pageResult.setTotal_page(totalPage);
        pageResult.setLast_page(isLastPage);

        return pageResult;
    }

    @Override
    public List<MethodBayarDto> getAll() throws ResourceNotExistException {
        List<MethodBayar> methodBayars = methodBayarMapper.getAll();

        if (methodBayars == null){
            throw new ResourceNotExistException("list is empty");
        }

        return methodBayars.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public MethodBayarDto getById(Long id) throws ResourceNotExistException {
        MethodBayar methodBayar = methodBayarMapper.getById(id);

        if (methodBayar == null){
            throw new ResourceNotExistException("that method bayar with id " + id + " not found");
        }

        return toDto(methodBayar);
    }

    @Override
    public void create(MethodBayarDto methodBayarDto) {
        MethodBayar methodBayar = new MethodBayar();
        methodBayar.setNamaMethod(methodBayarDto.getNamaMethod());
        methodBayar.setBiayaTransaksi(methodBayarDto.getBiayaTransaksi());
        methodBayar.setIdMataUang(methodBayarDto.getIdMataUang());

        methodBayarMapper.create(methodBayar);
    }

    @Override
    public void update(Long id, MethodBayarDto methodBayarDto) throws ResourceNotExistException,
            ResourceAlreadyException {
        MethodBayar checkExist = methodBayarMapper.getById(id);

        if (checkExist == null){
            throw new ResourceNotExistException("that method bayar with id " + id + " not found");
        }

        if (checkExist.getNamaMethod().equals(methodBayarDto.getNamaMethod())){
            throw new ResourceAlreadyException("that method bayar with " + methodBayarDto.getNamaMethod() + " already exist");
        }

        checkExist.setNamaMethod(methodBayarDto.getNamaMethod());
        checkExist.setBiayaTransaksi(methodBayarDto.getBiayaTransaksi());
        checkExist.setIdMataUang(methodBayarDto.getIdMataUang());

        methodBayarMapper.update(checkExist);
    }

    @Override
    public void delete(Long id) throws ResourceNotExistException {
        MethodBayar checkExist = methodBayarMapper.getById(id);

        if (checkExist == null){
            throw new ResourceNotExistException("that id with " + id + " not found");
        }

        methodBayarMapper.delete(id);
    }

    private MethodBayarDto toDto(MethodBayar methodBayar){
        return modelMapper.map(methodBayar, MethodBayarDto.class);
    }

    private MethodBayar toEntity(MethodBayarDto methodBayarDto){
        return modelMapper.map(methodBayarDto, MethodBayar.class);
    }
}
