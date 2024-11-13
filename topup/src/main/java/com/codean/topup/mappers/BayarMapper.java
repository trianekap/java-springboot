package com.codean.topup.mappers;

import com.codean.topup.models.entity.Bayar;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BayarMapper {
    void insert(Bayar bayar);

    Bayar getById(Long id);

    void delete(Long id);
}
