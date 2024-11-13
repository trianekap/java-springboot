package com.codean.topup.services;

import com.codean.topup.exceptions.ResourceAlreadyException;
import com.codean.topup.exceptions.ResourceNotExistException;
import com.codean.topup.models.dtos.BayarDto;
import com.codean.topup.models.dtos.GameDto;
import com.codean.topup.models.dtos.pageresult.PageResult;

import java.util.List;

public interface BayarService {
    PageResult<BayarDto> getAllByPage(int pageNumber, int pageSize) throws ResourceNotExistException;

    void insert(BayarDto bayarDto) throws ResourceAlreadyException;

    List<BayarDto> getAll() throws ResourceNotExistException;

    BayarDto getById(Long id) throws ResourceNotExistException;

    void delete(Long id) throws ResourceNotExistException;

}
