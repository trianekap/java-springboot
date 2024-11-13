package com.codean.topup.mappers;

import com.codean.topup.models.entity.PaketTopup;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PaketTopupMapper {
    List<PaketTopup> getAll();
    PaketTopup getById(Long id);

    void create(PaketTopup paketTopup);
    void update(PaketTopup paketTopup);
    void delete(Long id);
}
