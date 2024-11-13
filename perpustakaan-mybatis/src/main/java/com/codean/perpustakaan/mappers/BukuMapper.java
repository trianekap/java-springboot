package com.codean.perpustakaan.mappers;

import com.codean.perpustakaan.models.base.Buku;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Mapper
public interface BukuMapper {
    //user
    Optional<Buku> findById(@Param("id") UUID id);
    List<Buku> findAll();
    List<Buku> findByJudul(String judul);
    List<Buku> findByTahunTerbit(String tahunTerbit);

    //admin
    void update(Buku buku);
    void delete(@Param("id")UUID id);
    void save(Buku buku);
}
