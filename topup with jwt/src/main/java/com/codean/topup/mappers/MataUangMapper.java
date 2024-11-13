package com.codean.topup.mappers;

import com.codean.topup.models.dtos.MataUangDto;
import com.codean.topup.models.entity.MataUang;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MataUangMapper {
    List<MataUangDto> getAllByPage(Map<String, Object> params);
    Long countMataUang();

    List<MataUang> getAll();
    MataUang getById(Long id);

    void create(MataUang mataUang);
    void update(MataUang mataUang);
    void delete(Long id);
}
