package com.codean.topup.services;

import com.codean.topup.exceptions.ResourceAlreadyException;
import com.codean.topup.exceptions.ResourceNotExistException;
import com.codean.topup.models.dtos.PaketTopupDto;
import com.codean.topup.models.dtos.pageresult.PageResult;

import java.util.List;

public interface PaketTopupService {
    PageResult<PaketTopupDto> getAllByPage(int pageNumber, int pageSize) throws ResourceNotExistException;


    List<PaketTopupDto> getAll() throws ResourceNotExistException;
    PaketTopupDto getById(Long id) throws ResourceNotExistException;

    void create(PaketTopupDto paketTopupDto) throws ResourceAlreadyException;
    void update(Long id, PaketTopupDto paketTopupDto) throws ResourceNotExistException, ResourceAlreadyException;
    void delete(Long id) throws ResourceNotExistException;
}
