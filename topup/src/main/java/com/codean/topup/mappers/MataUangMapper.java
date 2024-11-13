package com.codean.topup.mappers;

import com.codean.topup.models.entity.MataUang;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MataUangMapper {
    List<MataUang> getAll();
    MataUang getById(Long id);

    void create(MataUang mataUang);
    void update(MataUang mataUang);
    void delete(Long id);
}
