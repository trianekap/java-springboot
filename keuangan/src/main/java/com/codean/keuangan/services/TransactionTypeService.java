package com.codean.keuangan.services;

import com.codean.keuangan.exceptions.ResourceNotExistException;
import com.codean.keuangan.models.dtos.TransactionTypeDto;
import com.codean.keuangan.models.dtos.updatedto.TransactionTypeUpdateDto;
import com.codean.keuangan.models.dtos.pageresult.PageResult;

import java.util.List;

public interface TransactionTypeService {
    PageResult<TransactionTypeDto> getAllByPage(int page, int pageSize);

    List<TransactionTypeDto> searchByName(String name);

    List<TransactionTypeDto> getAll() throws ResourceNotExistException;
    TransactionTypeDto getId(int id) throws ResourceNotExistException;
    void create(TransactionTypeDto transactionTypeDto);

    void update(int id, TransactionTypeUpdateDto transactionTypeUpdateDto) throws ResourceNotExistException;

    void delete(int id) throws ResourceNotExistException;
}
