package com.codean.keuangan.services;

import com.codean.keuangan.exceptions.ResourceNotExistException;
import com.codean.keuangan.models.dtos.TransactionDto;
import com.codean.keuangan.models.dtos.updatedto.TransactionUpdateDto;
import com.codean.keuangan.models.dtos.pageresult.PageResult;
import com.codean.keuangan.models.dtos.resultmodel.TransactionFilter;

import java.time.LocalDate;
import java.util.List;

public interface TransactionService {

    PageResult<TransactionDto> getAllByPage(int page, int pageSize);

    List<TransactionFilter> getByFilter(String username, String name, LocalDate startDate, LocalDate endDate,
                                        int startAmount, int endAmount);

    List<TransactionDto> searchByDate(LocalDate startDate, LocalDate endDate);

    List<TransactionDto> searchByAmount(int startAmount, int endAmount);

    List<TransactionDto> searchByDescription(String description);

    List<TransactionDto> getAll() throws ResourceNotExistException;
    TransactionDto getId(int id) throws ResourceNotExistException;
    void create(TransactionDto transactionDto);

    void update(int id, TransactionUpdateDto transactionUpdateDto) throws ResourceNotExistException;

    void delete(int id, boolean confirmed) throws ResourceNotExistException;
}
