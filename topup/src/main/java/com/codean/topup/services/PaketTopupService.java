package com.codean.topup.services;

import com.codean.topup.exceptions.ResourceAlreadyException;
import com.codean.topup.exceptions.ResourceNotExistException;
import com.codean.topup.models.dtos.PaketTopupDto;

import java.util.List;

public interface PaketTopupService {
    List<PaketTopupDto> getAll() throws ResourceNotExistException;
    PaketTopupDto getById(Long id) throws ResourceNotExistException;

    void create(PaketTopupDto paketTopupDto) throws ResourceAlreadyException;
    void update(Long id, PaketTopupDto paketTopupDto) throws ResourceNotExistException, ResourceAlreadyException;
    void delete(Long id) throws ResourceNotExistException;
}
