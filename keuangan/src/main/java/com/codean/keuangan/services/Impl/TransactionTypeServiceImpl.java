package com.codean.keuangan.services.Impl;

import com.codean.keuangan.exceptions.ResourceNotExistException;
import com.codean.keuangan.mappers.TransactionTypeMapper;
import com.codean.keuangan.models.dtos.TransactionTypeDto;
import com.codean.keuangan.models.dtos.updatedto.TransactionTypeUpdateDto;
import com.codean.keuangan.models.dtos.pageresult.PageResult;
import com.codean.keuangan.models.entity.TransactionType;
import com.codean.keuangan.services.TransactionTypeService;
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
public class TransactionTypeServiceImpl implements TransactionTypeService {

    private static final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    TransactionTypeMapper transactionTypeMapper;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public PageResult<TransactionTypeDto> getAllByPage(int page, int pageSize) {
        if (page < 1) page = 1;
        if (pageSize < 1) pageSize = 10;

        int offset = (page - 1) * pageSize;

        // Query to get paginated data
        Map<String, Object> params = new HashMap<>();
        params.put("offset", offset);
        params.put("limit", pageSize);
        List<TransactionTypeDto> transactionTypeDtos = transactionTypeMapper.getAllByPage(params);

        // Query to get total count
        Long totalElement = transactionTypeMapper.countTransactionType();

        // Hitung total halaman menggunakan PaginationHelper
        int totalPage = PaginationHelper.calculateTotalPages(totalElement, pageSize);

        // Determine if it is the last page
        boolean isLastPage = page >= totalPage;

        // Build PageResult
        PageResult<TransactionTypeDto> pageResult = new PageResult<>();
        pageResult.setContent(transactionTypeDtos);
        pageResult.setPage_number(page);
        pageResult.setPage_size(pageSize);
        pageResult.setTotal_element(totalElement);
        pageResult.setTotal_page(totalPage);
        pageResult.setLast_page(isLastPage);

        return pageResult;
    }

    @Override
    public List<TransactionTypeDto> searchByName(String name) {
        List<TransactionTypeDto> transactionTypeDtoList = transactionTypeMapper.searchByName(name);

        return transactionTypeDtoList.stream().collect(Collectors.toList());
    }

    @Override
    public List<TransactionTypeDto> getAll() throws ResourceNotExistException {
        return transactionTypeMapper.getAll().stream().map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TransactionTypeDto getId(int id) throws ResourceNotExistException {
        TransactionType transactionType = transactionTypeMapper.getId(id);

        if (transactionType == null){
            String message = "that id not exist";
            log.error(message);
            throw new ResourceNotExistException(message);
        }

        return toDto(transactionType);
    }

    @Override
    public void create(TransactionTypeDto transactionTypeDto) {

        TransactionType transactionType = new TransactionType();
        transactionType.setName(transactionTypeDto.getName());

        transactionTypeMapper.create(transactionType);
    }

    @Override
    public void update(int id, TransactionTypeUpdateDto transactionTypeUpdateDto) throws ResourceNotExistException {
        TransactionType transactionType = transactionTypeMapper.getId(id);

        if (transactionType == null){
            String message = "that id not exist";
            log.error(message);
            throw new ResourceNotExistException(message);
        }

        transactionType.setName(transactionTypeUpdateDto.getName());

        transactionTypeMapper.update(transactionType);
    }

    @Override
    public void delete(int id) throws ResourceNotExistException{
        TransactionType transactionType = transactionTypeMapper.getId(id);
        if (transactionType == null){
            String message = "that id not exist";
            log.error(message);
            throw new ResourceNotExistException(message);
        }

        transactionTypeMapper.delete(id);
    }


    private TransactionTypeDto toDto(TransactionType transactionType){
        return modelMapper.map(transactionType, TransactionTypeDto.class);
    }

    private TransactionType toEntity(TransactionTypeDto transactionTypeDto){
        return modelMapper.map(transactionTypeDto, TransactionType.class);
    }
}
