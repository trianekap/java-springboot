package com.codean.topup.mappers;

import com.codean.topup.models.dtos.MethodBayarDto;
import com.codean.topup.models.entity.MethodBayar;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MethodBayarMapper {
    List<MethodBayarDto> getAllByPage(Map<String, Object> params);
    Long countMethodBayar();

    List<MethodBayar> getAll();
    MethodBayar getById(Long id);

    void create(MethodBayar methodBayar);
    void update(MethodBayar methodBayar);
    void delete(Long id);
}
