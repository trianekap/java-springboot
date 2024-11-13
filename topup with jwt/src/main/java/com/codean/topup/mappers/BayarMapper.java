package com.codean.topup.mappers;

import com.codean.topup.models.dtos.BayarDto;
import com.codean.topup.models.entity.Bayar;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BayarMapper {
    List<BayarDto> getAllByPage(Map<String, Object> params);
    Long countBayar();

    void insert(Bayar bayar);

    List<Bayar> getAll();

    Bayar getById(Long id);

    void delete(Long id);
}
