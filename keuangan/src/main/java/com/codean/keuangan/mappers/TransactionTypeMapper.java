package com.codean.keuangan.mappers;

import com.codean.keuangan.models.dtos.TransactionTypeDto;
import com.codean.keuangan.models.entity.TransactionType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TransactionTypeMapper {
    List<TransactionTypeDto> getAllByPage(Map<String, Object> params);

    Long countTransactionType();

    List<TransactionTypeDto> searchByName(String name);

    List<TransactionType> getAll();
    TransactionType getId(int id);
    void create(TransactionType transactionType);
    void update(TransactionType transactionType);
    void delete(int id);
}
