package com.codean.mybatispesantren.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PresensiMapper {

    void insert(int idSantri, int idKegiatan, String status);
    void delete(int idSantri, int idKegiatan, String status);
}
