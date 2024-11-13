package com.codean.keuangan.mappers;

import com.codean.keuangan.models.dtos.TransactionDto;
import com.codean.keuangan.models.dtos.resultmodel.TransactionFilter;
import com.codean.keuangan.models.entity.Transaction;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface TransactionMapper {

    List<TransactionDto> getAllByPage(Map<String, Object> params);
    Long countTransactions();

    List<TransactionDto> searchByDescription(String description);

    List<TransactionDto> searchByDate(LocalDate startDate, LocalDate endDate);

    List<TransactionDto> searchByAmount(int startAmount, int endAmount);


    List<TransactionFilter> getByFilter(String username, String name, LocalDate startDate,
                                        LocalDate endDate, int startAmount, int endAmount);

    List<Transaction> getAll();
    Transaction getId(int id);
    void create(Transaction transaction);
    void update(Transaction transaction);
    void delete(int id);
}
