package com.codean.keuangan.mappers;

import com.codean.keuangan.models.dtos.AccountDto;
import com.codean.keuangan.models.entity.Account;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AccountMapper {
    List<AccountDto> getAllByPage(Map<String, Object> params);

    Long countAccount();

    List<AccountDto> searchByBalance(int startBalance, int endBalance);

    List<Account> getAll();
    Account getId(int id);
    List<Account> searchByAccountName(String accountName);
    void create(Account account);
    void update(Account account);
    void delete(int id);
}
