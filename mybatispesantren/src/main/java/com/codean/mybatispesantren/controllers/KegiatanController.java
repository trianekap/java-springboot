package com.codean.mybatispesantren.controllers;

import com.codean.mybatispesantren.mapper.KegiatanMapper;
import com.codean.mybatispesantren.model.post.Kegiatan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kegiatan")
public class KegiatanController {

    @Autowired
    KegiatanMapper kegiatanMapper;

    @GetMapping("/getall")
    public List<Kegiatan> getAllKegiatan(){
        return kegiatanMapper.findAllKegiatan();
    }

    @GetMapping("/getkegiatanbyid/{idKegiatan}")
    public Kegiatan findKegiatan(@PathVariable int idKegiatan){
        return kegiatanMapper.findKegiatan(idKegiatan);
    }

    @GetMapping("/getkegiatanbyiddetail/{idKegiatan}")
    public Kegiatan getDetailKegiatan(@PathVariable int idKegiatan){
        return kegiatanMapper.findDetailedKegiatan(idKegiatan);
    }

    @PostMapping("/addkegiatan")
    public String addKegiatan(@RequestBody Kegiatan kegiatan){
        kegiatanMapper.insertKegiatan(kegiatan);
        return "berhasil menambahkan kegiatan";
    }

    @PostMapping("/addmanykegiatan")
    public List<Kegiatan> addManyKegiatan(@RequestBody Kegiatan[] kegiatans){
        for (Kegiatan k : kegiatans){
            addKegiatan(k);
        }

        return getAllKegiatan();
    }

    @PutMapping("/updatekegiatan/{id}")
    public List<Kegiatan> updateKegiatan(@PathVariable int id, @RequestBody Kegiatan kegiatan){
        kegiatanMapper.updateKegiatan(id, kegiatan);
        return getAllKegiatan();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteKegiatan(@PathVariable int id){
        kegiatanMapper.deleteKegiatan(id);
        return "berhasil menghapus data kegiatan";
    }
}
