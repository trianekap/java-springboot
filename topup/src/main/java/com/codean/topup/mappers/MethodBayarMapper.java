package com.codean.topup.mappers;

import com.codean.topup.models.entity.MethodBayar;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MethodBayarMapper {
    List<MethodBayar> getAll();
    MethodBayar getById(Long id);

    void create(MethodBayar methodBayar);
    void update(MethodBayar methodBayar);
    void delete(Long id);
}
