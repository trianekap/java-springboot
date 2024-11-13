package com.codean.keuangan.controllers;

import com.codean.keuangan.exceptions.ResourceNotExistException;
import com.codean.keuangan.models.dtos.TransactionTypeDto;
import com.codean.keuangan.models.dtos.updatedto.TransactionTypeUpdateDto;
import com.codean.keuangan.models.dtos.pageresult.PageResult;
import com.codean.keuangan.services.TransactionTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transactionType")
public class TransactionTypeController {
    private static final Logger log = LoggerFactory.getLogger(AccountController.class);
    @Autowired
    TransactionTypeService transactionTypeService;

    @GetMapping("/getAllByPage")
    public PageResult<TransactionTypeDto> getAllByPage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize) {

        return transactionTypeService.getAllByPage(page, pageSize);
    }

    @GetMapping("/searchByName")
    public List<TransactionTypeDto> searchByName(String name){
        List<TransactionTypeDto> transactionTypeDtos = transactionTypeService.searchByName(name);

        return transactionTypeDtos.stream().collect(Collectors.toList());
    }

    @PostMapping("/create")
    public String create(@RequestBody @Valid TransactionTypeDto transactionTypeDto){
        transactionTypeService.create(transactionTypeDto);
        return "transaction type has been added";
    }

    @GetMapping("/getAll")
    public List<TransactionTypeDto> getAll() throws ResourceNotExistException {
        List<TransactionTypeDto> transactionTypeDtos = transactionTypeService.getAll();
        if (transactionTypeDtos.isEmpty()){
            String message = "list is empty";
            log.info(message);
            throw new ResourceNotExistException(message);
        }

        return transactionTypeService.getAll();
    }

    @GetMapping("/getId/{id}")
    public TransactionTypeDto getId(@PathVariable int id) throws ResourceNotExistException{
        TransactionTypeDto transactionTypeDto = transactionTypeService.getId(id);
        return transactionTypeDto;
    }

    @PutMapping("/update/{id}")
    public List<TransactionTypeDto> update(@PathVariable int id,
                                    @RequestBody @Valid TransactionTypeUpdateDto transactionTypeUpdateDto)
            throws ResourceNotExistException{

        transactionTypeService.update(id, transactionTypeUpdateDto);

        return getAll();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id) throws ResourceNotExistException{
        transactionTypeService.delete(id);

        return "transaction type has been deleted";
    }
}
