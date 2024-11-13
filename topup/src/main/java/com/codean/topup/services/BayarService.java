package com.codean.topup.services;

import com.codean.topup.exceptions.ResourceAlreadyException;
import com.codean.topup.exceptions.ResourceNotExistException;
import com.codean.topup.models.dtos.BayarDto;

public interface BayarService {

    void insert(BayarDto bayarDto) throws ResourceAlreadyException;

    BayarDto getById(Long id) throws ResourceNotExistException;

    void delete(Long id) throws ResourceNotExistException;

}
