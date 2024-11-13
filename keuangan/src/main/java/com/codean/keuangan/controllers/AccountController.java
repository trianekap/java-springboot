package com.codean.keuangan.controllers;


import com.codean.keuangan.exceptions.ResourceNotExistException;
import com.codean.keuangan.models.dtos.AccountDto;
import com.codean.keuangan.models.dtos.updatedto.AccountUpdateDto;
import com.codean.keuangan.models.dtos.pageresult.PageResult;
import com.codean.keuangan.services.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/account")
public class AccountController {

    private static final Logger log = LoggerFactory.getLogger(AccountController.class);
    @Autowired
    AccountService accountService;

    @GetMapping("/getAllByPage")
    public PageResult<AccountDto> getAllByPage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize) {

        return accountService.getAllByPage(page, pageSize);
    }

    @PostMapping("/create")
    public String create(@RequestBody @Valid AccountDto accountDto){
        accountService.create(accountDto);
        return "account has been added";
    }

    @GetMapping("/getAll")
    public List<AccountDto> getAll() throws ResourceNotExistException {
        List<AccountDto> accountDtoList = accountService.getAll();
        if (accountDtoList.isEmpty()){
            String message = "list is empty";
            log.info(message);
            throw new ResourceNotExistException(message);
        }

        return accountService.getAll();
    }

    @GetMapping("/getId/{id}")
    public AccountDto getId(@PathVariable int id) throws ResourceNotExistException{
        AccountDto accountDto = accountService.getId(id);
        return accountDto;
    }

    @GetMapping("/searchByAccountName")
    public List<AccountDto> searchByAccountName(String accountName){
        List<AccountDto> accountList = accountService.searchByAccountName(accountName);

        return accountList.stream().collect(Collectors.toList());
    }

    @GetMapping("/searchByBalance")
    public List<AccountDto> searchByBalance(int startBalance, int endBalance){
        List<AccountDto> accountDtoList = accountService.searchByBalance(startBalance, endBalance);

        return accountDtoList.stream().collect(Collectors.toList());
    }

    @PutMapping("/update/{id}")
    public List<AccountDto> update(@PathVariable int id,
                                @RequestBody @Valid AccountUpdateDto accountUpdateDto)
            throws ResourceNotExistException{

        accountService.update(id, accountUpdateDto);

        return getAll();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id) throws ResourceNotExistException{
        accountService.delete(id);

        return "account has been deleted";
    }
}
