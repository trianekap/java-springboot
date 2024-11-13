package com.codean.perpustakaan.mappers;

import com.codean.perpustakaan.models.base.Pinjam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Mapper
public interface PinjamMapper {

    //user
    Pinjam findById(@Param("id") UUID id);
    List<Pinjam> findAll();

    //admin
    void update(Pinjam pinjam);
    void delete(@Param("id")UUID id);
    void save(Pinjam pinjam);
}
