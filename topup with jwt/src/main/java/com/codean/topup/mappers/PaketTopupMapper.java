package com.codean.topup.mappers;

import com.codean.topup.models.dtos.PaketTopupDto;
import com.codean.topup.models.entity.PaketTopup;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PaketTopupMapper {
    List<PaketTopupDto> getAllByPage(Map<String, Object> params);
    Long countPaketTopup();

    List<PaketTopup> getAll();
    PaketTopup getById(Long id);

    void create(PaketTopup paketTopup);
    void update(PaketTopup paketTopup);
    void delete(Long id);
}
