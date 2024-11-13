package com.codean.keuangan.services.Impl;

import com.codean.keuangan.exceptions.ResourceNotExistException;
import com.codean.keuangan.mappers.AccountMapper;
import com.codean.keuangan.models.dtos.AccountDto;
import com.codean.keuangan.models.dtos.updatedto.AccountUpdateDto;
import com.codean.keuangan.models.dtos.pageresult.PageResult;
import com.codean.keuangan.models.entity.Account;
import com.codean.keuangan.services.AccountService;
import com.codean.keuangan.util.PaginationHelper;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);


    @Autowired
    AccountMapper accountMapper;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public PageResult<AccountDto> getAllByPage(int page, int pageSize) {
        if (page < 1) page = 1;
        if (pageSize < 1) pageSize = 10;

        int offset = (page - 1) * pageSize;

        // Query to get paginated data
        Map<String, Object> params = new HashMap<>();
        params.put("offset", offset);
        params.put("limit", pageSize);
        List<AccountDto> accountDtoList = accountMapper.getAllByPage(params);

        // Query to get total count
        Long totalElement = accountMapper.countAccount();

        // Hitung total halaman menggunakan PaginationHelper
        int totalPage = PaginationHelper.calculateTotalPages(totalElement, pageSize);

        // Determine if it is the last page
        boolean isLastPage = page >= totalPage;

        // Build PageResult
        PageResult<AccountDto> pageResult = new PageResult<>();
        pageResult.setContent(accountDtoList);
        pageResult.setPage_number(page);
        pageResult.setPage_size(pageSize);
        pageResult.setTotal_element(totalElement);
        pageResult.setTotal_page(totalPage);
        pageResult.setLast_page(isLastPage);

        return pageResult;
    }

    @Override
    public List<AccountDto> searchByBalance(int startBalance, int endBalance) {
        List<AccountDto> accountDtoList = accountMapper.searchByBalance(startBalance, endBalance);

        return accountDtoList.stream().collect(Collectors.toList());
    }

    @Override
    public List<AccountDto> getAll() throws ResourceNotExistException {
        return accountMapper.getAll().stream().map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto getId(int id) throws ResourceNotExistException{
        Account account = accountMapper.getId(id);

        if (account == null){
            String message = "that id not exist";
            log.error(message);
            throw new ResourceNotExistException(message);
        }

        return toDto(account);
    }

    @Override
    public List<AccountDto> searchByAccountName(String accountName) {
        List<Account> accountList = accountMapper.searchByAccountName(accountName);

        return accountList.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public void create(AccountDto accountDto) {

        Account account = new Account();
        account.setUserId(accountDto.getUserId());
        account.setAccountName(accountDto.getAccountName());
        account.setBalance(accountDto.getBalance());

        accountMapper.create(account);
    }

    @Override
    public void update(int id, AccountUpdateDto accountUpdateDto) throws ResourceNotExistException {
        Account account = accountMapper.getId(id);

        if (account == null){
            String message = "that id not exist";
            log.error(message);
            throw new ResourceNotExistException(message);
        }

        account.setUserId(accountUpdateDto.getUserId());
        account.setAccountName(accountUpdateDto.getAccountName());
        account.setBalance(accountUpdateDto.getBalance());

        accountMapper.update(account);
    }

    @Override
    public void delete(int id) throws ResourceNotExistException{
        Account account = accountMapper.getId(id);
        if (account == null){
            String message = "that id not exist";
            log.error(message);
            throw new ResourceNotExistException(message);
        }

        accountMapper.delete(id);
    }


    private AccountDto toDto(Account account){
        return modelMapper.map(account, AccountDto.class);
    }

    private Account toEntity(AccountDto accountDto){
        return modelMapper.map(accountDto, Account.class);
    }
}
