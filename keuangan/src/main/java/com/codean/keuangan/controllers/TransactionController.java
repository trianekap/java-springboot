package com.codean.keuangan.controllers;

import com.codean.keuangan.exceptions.ResourceNotExistException;
import com.codean.keuangan.models.dtos.TransactionDto;
import com.codean.keuangan.models.dtos.updatedto.TransactionUpdateDto;
import com.codean.keuangan.models.dtos.pageresult.PageResult;
import com.codean.keuangan.models.dtos.resultmodel.TransactionFilter;
import com.codean.keuangan.services.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private static final Logger log = LoggerFactory.getLogger(AccountController.class);
    @Autowired
    TransactionService transactionService;

    @GetMapping("/getAllByPage")
    public PageResult<TransactionDto> getAllByPage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize) {

        return transactionService.getAllByPage(page, pageSize);
    }

    @GetMapping("/searchByDescription")
    public List<TransactionDto> searchByDescription(String description){
        List<TransactionDto> transactionDtoList = transactionService.searchByDescription(description);

        return transactionDtoList.stream().collect(Collectors.toList());
    }

    @GetMapping("/searchByDate")
    public List<TransactionDto> searchByDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                             @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate){

        List<TransactionDto> transactionDtoList = transactionService.searchByDate(startDate, endDate);

        return transactionDtoList.stream().collect(Collectors.toList());
    }

    @GetMapping("/searchByAmount")
    public List<TransactionDto> seacrhByAmount(@RequestParam int startAmount, @RequestParam int endAmount){
        List<TransactionDto> transactionDtoList = transactionService.searchByAmount(startAmount, endAmount);

        return transactionDtoList.stream().collect(Collectors.toList());
    }

    @GetMapping("/getByFilter")
    public List<TransactionFilter> getByFilter(@RequestParam String username, @RequestParam String name,
                                               @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                               @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
                                               @RequestParam int startAmount, @RequestParam int endAmount){

        List<TransactionFilter> transactionFilterList = transactionService.getByFilter(username,
                name, startDate, endDate, startAmount, endAmount);

        return transactionFilterList.stream().collect(Collectors.toList());
    }

    @PostMapping("/create")
    public String create(@RequestBody @Valid TransactionDto transactionDto){
        transactionService.create(transactionDto);
        return "transaction has been added";
    }

    @GetMapping("/getAll")
    public List<TransactionDto> getAll() throws ResourceNotExistException {
        List<TransactionDto> transactionDtos = transactionService.getAll();
        if (transactionDtos.isEmpty()){
            String message = "list is empty";
            log.info(message);
            throw new ResourceNotExistException(message);
        }

        return transactionService.getAll();
    }

    @GetMapping("/getId/{id}")
    public TransactionDto getId(@PathVariable int id) throws ResourceNotExistException{
        TransactionDto transactionDto = transactionService.getId(id);
        return transactionDto;
    }

    @PutMapping("/update/{id}")
    public List<TransactionDto> update(@PathVariable int id,
                                           @RequestBody @Valid TransactionUpdateDto transactionUpdateDto)
            throws ResourceNotExistException{

        transactionService.update(id, transactionUpdateDto);

        return getAll();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id, @RequestParam(defaultValue = "false") boolean confirmed) throws ResourceNotExistException{
        try {
            // Panggil metode service untuk menghapus transaksi dengan konfirmasi
            transactionService.delete(id, confirmed);
            return "Transaction has been deleted.";
        } catch (ResourceNotExistException e) {
            // Menangani kasus ketika ID tidak ditemukan
            return "Error: " + e.getMessage();
        } catch (IllegalStateException e) {
            // Menangani kasus ketika penghapusan tidak dikonfirmasi
            return "Error: " + e.getMessage();
        }
    }
}
