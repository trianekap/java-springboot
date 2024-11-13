package com.codean.keuangan.services;

import com.codean.keuangan.exceptions.ResourceNotExistException;
import com.codean.keuangan.models.dtos.AccountDto;
import com.codean.keuangan.models.dtos.updatedto.AccountUpdateDto;
import com.codean.keuangan.models.dtos.pageresult.PageResult;

import java.util.List;

public interface AccountService {
    PageResult<AccountDto> getAllByPage(int page, int pageSize);

    List<AccountDto> searchByBalance(int startBalance, int endBalance);

    List<AccountDto> getAll() throws ResourceNotExistException;
    AccountDto getId(int id) throws ResourceNotExistException;
    List<AccountDto> searchByAccountName(String accountName);
    void create(AccountDto accountDto);

    void update(int id, AccountUpdateDto accountUpdateDto) throws ResourceNotExistException;

    void delete(int id) throws ResourceNotExistException;
}
