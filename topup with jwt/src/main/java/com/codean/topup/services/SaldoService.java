package com.codean.topup.services;

import com.codean.topup.exceptions.ResourceAlreadyException;
import com.codean.topup.exceptions.ResourceNotExistException;
import com.codean.topup.models.dtos.SaldoDto;
import com.codean.topup.models.dtos.pageresult.PageResult;

import java.util.List;

public interface SaldoService {
    PageResult<SaldoDto> getAllByPage(int pageNumber, int pageSize) throws ResourceNotExistException;

    List<SaldoDto> getAll() throws ResourceNotExistException;
    SaldoDto getId(Long id) throws ResourceNotExistException;

    void create (SaldoDto saldoDto) throws ResourceAlreadyException;
    void update (Long id, SaldoDto saldoDto) throws ResourceNotExistException;
    void delete (Long id) throws ResourceNotExistException;
}
