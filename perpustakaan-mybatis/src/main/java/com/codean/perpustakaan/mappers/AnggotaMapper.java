package com.codean.perpustakaan.mappers;

import com.codean.perpustakaan.models.base.Anggota;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.UUID;

@Mapper
public interface AnggotaMapper {

    //user
    Anggota findById(@Param("id")UUID id);
    List<Anggota> findAll();
    List<Anggota> findByName(String name);

    //admin
    void update(Anggota anggota);
    void delete(@Param("id")UUID id);
    void save(Anggota anggota);

}
