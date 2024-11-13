package com.codean.topup.services;

import com.codean.topup.exceptions.ResourceAlreadyException;
import com.codean.topup.exceptions.ResourceNotExistException;
import com.codean.topup.models.dtos.MataUangDto;

import java.util.List;

public interface MataUangService {

    List<MataUangDto> getAll() throws ResourceNotExistException;
    MataUangDto getById(Long id) throws ResourceNotExistException;

    void create(MataUangDto mataUangDto) throws ResourceAlreadyException;
    void update(Long id, MataUangDto mataUangDto) throws ResourceNotExistException, ResourceAlreadyException;
    void delete(Long id) throws ResourceNotExistException;
}
