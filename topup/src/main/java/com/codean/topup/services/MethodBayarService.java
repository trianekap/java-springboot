package com.codean.topup.services;

import com.codean.topup.exceptions.ResourceAlreadyException;
import com.codean.topup.exceptions.ResourceNotExistException;
import com.codean.topup.models.dtos.MethodBayarDto;

import java.util.List;

public interface MethodBayarService {
    List<MethodBayarDto> getAll() throws ResourceNotExistException;
    MethodBayarDto getById(Long id) throws ResourceNotExistException;

    void create(MethodBayarDto methodBayarDto) throws ResourceAlreadyException;
    void update(Long id, MethodBayarDto methodBayarDto) throws ResourceNotExistException, ResourceAlreadyException;
    void delete(Long id) throws ResourceNotExistException;
}
