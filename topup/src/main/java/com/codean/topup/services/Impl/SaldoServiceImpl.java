package com.codean.topup.services.Impl;


import com.codean.topup.exceptions.ResourceNotExistException;
import com.codean.topup.mappers.SaldoMapper;
import com.codean.topup.models.dtos.SaldoDto;
import com.codean.topup.models.dtos.pageresult.PageResult;
import com.codean.topup.models.entity.Saldo;
import com.codean.topup.services.SaldoService;
import com.codean.topup.util.PaginationHelper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SaldoServiceImpl implements SaldoService {

    @Autowired
    SaldoMapper saldoMapper;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public PageResult<SaldoDto> getAllByPage(int pageNumber, int pageSize) throws ResourceNotExistException {
        if (pageNumber < 1) pageNumber = 1;
        if (pageSize < 1) pageSize = 10;

        int offset = (pageNumber - 1) * pageSize;

        Map<String, Object> params = new HashMap<>();
        params.put("offset", offset);
        params.put("limit", pageSize);
        List<SaldoDto> saldoDtos = saldoMapper.getAllByPage(params);

        Long totalElement = saldoMapper.countSaldo();

        int totalPage = PaginationHelper.calculateTotalPages(totalElement, pageSize);

        boolean isLastPage;
        isLastPage = pageNumber >= totalPage;

        PageResult<SaldoDto> pageResult = new PageResult<>();
        pageResult.setContent(saldoDtos);
        pageResult.setPage_number(pageNumber);
        pageResult.setPage_size(pageSize);
        pageResult.setTotal_element(totalElement);
        pageResult.setTotal_page(totalPage);
        pageResult.setLast_page(isLastPage);

        return pageResult;
    }

    @Override
    public List<SaldoDto> getAll() throws ResourceNotExistException {
        List<Saldo> saldoList = saldoMapper.getAll();

        if (saldoList == null){
            throw new ResourceNotExistException("list is empty!");
        }

        return saldoList.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public SaldoDto getId(Long id) throws ResourceNotExistException{
        Saldo saldo = saldoMapper.getId(id);

        if (saldo == null){
            throw new ResourceNotExistException("saldo with id " + id + " not found!");
        }

        return toDto(saldo);
    }

    @Override
    public void create(SaldoDto saldoDto) {

        Saldo saldo = new Saldo();
        saldo.setJenisSaldo(saldoDto.getJenisSaldo());
        saldo.setJumlahSaldo(saldoDto.getJumlahSaldo());
        saldo.setIdUser(saldoDto.getIdUser());

        saldoMapper.create(saldo);
    }

    @Override
    public void update(Long idSaldo, SaldoDto saldoDto) throws ResourceNotExistException{

        Saldo saldo = saldoMapper.getId(idSaldo);

        if (saldo == null){
            throw new ResourceNotExistException("user with id " + idSaldo + " not found!");
        }

        saldo.setJenisSaldo(saldoDto.getJenisSaldo());
        saldo.setJumlahSaldo(saldoDto.getJumlahSaldo());
        saldo.setIdUser(saldoDto.getIdUser());

        saldoMapper.update(saldo);

    }

    @Override
    public void delete(Long id) throws ResourceNotExistException{
        Saldo saldo = saldoMapper.getId(id);

        if (saldo == null){
            throw new ResourceNotExistException("user with id " + id + " not found!");
        }

        saldoMapper.delete(id);
    }

    private SaldoDto toDto(Saldo saldo){
        return modelMapper.map(saldo, SaldoDto.class);
    }

    private Saldo toEntity(SaldoDto saldoDto){
        return modelMapper.map(saldoDto, Saldo.class);
    }
}
