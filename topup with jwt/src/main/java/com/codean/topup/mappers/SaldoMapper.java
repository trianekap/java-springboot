package com.codean.topup.mappers;

import com.codean.topup.models.dtos.SaldoDto;
import com.codean.topup.models.entity.Saldo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SaldoMapper {

    List<SaldoDto> getAllByPage(Map<String, Object> params);
    Long countSaldo();

    List<Saldo> getAll();
    Saldo getId(Long id);

    void create (Saldo saldo);
    void update (Saldo saldo);
    void delete (Long id);
}
