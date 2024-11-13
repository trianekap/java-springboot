package com.codean.keuangan.services.Impl;

import com.codean.keuangan.exceptions.ResourceNotExistException;
import com.codean.keuangan.mappers.*;
import com.codean.keuangan.models.dtos.TransactionDto;
import com.codean.keuangan.models.dtos.updatedto.TransactionUpdateDto;
import com.codean.keuangan.models.dtos.pageresult.PageResult;
import com.codean.keuangan.models.dtos.resultmodel.TransactionFilter;
import com.codean.keuangan.models.entity.*;
import com.codean.keuangan.services.TransactionService;
import com.codean.keuangan.util.PaginationHelper;
import lombok.var;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    private static final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    TransactionMapper transactionMapper;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    AccountMapper accountMapper;

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    TransactionTypeMapper transactionTypeMapper;


    @Override
    public PageResult<TransactionDto> getAllByPage(int page, int pageSize) {
        if (page < 1) page = 1;
        if (pageSize < 1) pageSize = 10;

        int offset = (page - 1) * pageSize;

        Map<String, Object> params = new HashMap<>();
        params.put("offset", offset);
        params.put("limit", pageSize);
        List<TransactionDto> transactionDtos = transactionMapper.getAllByPage(params);

        Long totalElement = transactionMapper.countTransactions();

        int totalPage = PaginationHelper.calculateTotalPages(totalElement, pageSize);

        boolean isLastPage = page >= totalPage;

        PageResult<TransactionDto> pageResult = new PageResult<>();
        pageResult.setContent(transactionDtos);
        pageResult.setPage_number(page);
        pageResult.setPage_size(pageSize);
        pageResult.setTotal_element(totalElement);
        pageResult.setTotal_page(totalPage);
        pageResult.setLast_page(isLastPage);

        return pageResult;
    }

    @Override
    public List<TransactionFilter> getByFilter(String username, String name, LocalDate startDate, LocalDate endDate,
                                               int startAmount, int endAmount) {
        List<TransactionFilter> transactionFilter = transactionMapper.getByFilter(username,
                name, startDate, endDate, startAmount, endAmount);

        return transactionFilter.stream().collect(Collectors.toList());
    }

    @Override
    public List<TransactionDto> searchByDate(LocalDate startDate, LocalDate endDate) {
        List<TransactionDto> transactionDtoList = transactionMapper.searchByDate(startDate, endDate);

        return transactionDtoList.stream().collect(Collectors.toList());
    }

    @Override
    public List<TransactionDto> searchByAmount(int startAmount, int endAmount) {
        List<TransactionDto> transactionDtoList =transactionMapper.searchByAmount(startAmount, endAmount);

        return transactionDtoList.stream().collect(Collectors.toList());
    }

    @Override
    public List<TransactionDto> searchByDescription(String description) {
        List<TransactionDto> transactionDtoList = transactionMapper.searchByDescription(description);

        return transactionDtoList.stream().collect(Collectors.toList());
    }

    @Override
    public List<TransactionDto> getAll() throws ResourceNotExistException {
        return transactionMapper.getAll().stream().map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TransactionDto getId(int id) throws ResourceNotExistException {
        Transaction transaction = transactionMapper.getId(id);

        if (transaction == null){
            String message = "that id not exist";
            log.error(message);
            throw new ResourceNotExistException(message);
        }

        return toDto(transaction);
    }

    @Override
    public void create(TransactionDto transactionDto) {

        User user = userMapper.getId(transactionDto.getUserId());
        if (user == null){
            throw new RuntimeException("user id not found");
        }

        Account account = accountMapper.getId(transactionDto.getAccountId());
        if (account == null){
            throw new RuntimeException("account id not found");
        }

        Category category = categoryMapper.getId(transactionDto.getCategoryId());
        if (category == null){
            throw new RuntimeException("category id not found");
        }

        TransactionType transactionType = transactionTypeMapper.getId(transactionDto.getTransactionTypeId());
        if (transactionType == null){
            throw new RuntimeException("transaction type id not found");
        }

        Transaction transaction = new Transaction();
        transaction.setUserId(transactionDto.getUserId());
        transaction.setAccountId(transactionDto.getAccountId());
        transaction.setCategoryId(transactionDto.getCategoryId());
        transaction.setTransactionTypeId(transactionDto.getTransactionTypeId());
        transaction.setDate(transactionDto.getDate());
        transaction.setDescription(transactionDto.getDescription());
        transaction.setAmount(transactionDto.getAmount());

        transactionMapper.create(transaction);

        /*
        karena transaction type nya ada 2 pembelian dan penjualan
        maka di kondisi ini di set hanya ada 2 saja
        */
        int hasil;
        if(transactionType.getId() == 1) {
            hasil = account.getBalance() - transaction.getAmount();
        } else if (transactionType.getId() == 2){
            hasil = account.getBalance() + transaction.getAmount();
        } else {
            throw new RuntimeException("transaction type id tidak valid");
        }

        account.setBalance(hasil);

        accountMapper.update(account);
    }

    @Override
    public void update(int id, TransactionUpdateDto transactionUpdateDto) throws ResourceNotExistException {
        Transaction transaction = transactionMapper.getId(id);

        if (transaction == null){
            String message = "that id not exist";
            log.error(message);
            throw new ResourceNotExistException(message);
        }

        transaction.setUserId(transactionUpdateDto.getUserId());
        transaction.setAccountId(transactionUpdateDto.getAccountId());
        transaction.setCategoryId(transactionUpdateDto.getCategoryId());
        transaction.setTransactionTypeId(transactionUpdateDto.getTransactionTypeId());
        transaction.setDate(transactionUpdateDto.getDate());
        transaction.setDescription(transactionUpdateDto.getDescription());
        transaction.setAmount(transactionUpdateDto.getAmount());

        transactionMapper.update(transaction);
    }

    @Override
    public void delete(int id, boolean confirmed) throws ResourceNotExistException{
        Transaction transaction = transactionMapper.getId(id);
        if (transaction == null){
            String message = "that id not exist";
            log.error(message);
            throw new ResourceNotExistException(message);
        }

        if (!confirmed) {
            String message = "Deletion not confirmed";
            log.warn(message);
            throw new IllegalStateException(message);
        }

        transactionMapper.delete(id);
    }


    private TransactionDto toDto(Transaction transaction){
        return modelMapper.map(transaction, TransactionDto.class);
    }

    private Transaction toEntity(TransactionDto transactionDto){
        return modelMapper.map(transactionDto, Transaction.class);
    }
}
