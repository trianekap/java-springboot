package com.codean.mybatispesantren.controllers;

import com.codean.mybatispesantren.exceptions.ResourceNotExistException;
import com.codean.mybatispesantren.model.dtos.kegiatan.KegiatanDTO;
import com.codean.mybatispesantren.model.dtos.kegiatan.KegiatanGetAllDTO;
import com.codean.mybatispesantren.model.entity.Kegiatan;
import com.codean.mybatispesantren.services.KegiatanService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/kegiatan")
public class KegiatanController {

    private static final Logger logger = LoggerFactory.getLogger(KegiatanController.class);
    
    
    @Autowired
    KegiatanService kegiatanService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/getallbiasa")
    public List<KegiatanGetAllDTO> getAll(){
        return kegiatanService.getAll();
    }

    @GetMapping("/getall/{id}")
    public List<Kegiatan> getAllKegiatan(@PathVariable int id){
        return kegiatanService.findAllKegiatan(id);
    }

    @GetMapping("/getbyname")
    public List<KegiatanGetAllDTO> getByName(@RequestParam(defaultValue = "") String nama){
        List<KegiatanGetAllDTO> kegiatanList = kegiatanService.findByName(nama);
        return kegiatanList;
    }

    @GetMapping("/getdetail/{idKegiatan}")
    public List<KegiatanDTO> getDetail(@PathVariable int idKegiatan){
        return kegiatanService.findDetailKegiatan(idKegiatan);
    }

    @GetMapping("/getbydate")
    public List<KegiatanDTO> getByDate (
            @RequestParam(defaultValue = "2024-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(defaultValue = "2025-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) throws ResourceNotExistException {

        List<KegiatanDTO> kegiatanDTOList = kegiatanService.findByDate(startDate, endDate);

        return kegiatanDTOList;
    }

    @GetMapping("/getkegiatanbyid/{idKegiatan}")
    public Kegiatan findKegiatan(@PathVariable int idKegiatan) throws ResourceNotExistException {
        return kegiatanService.findKegiatanById(idKegiatan);
    }

    @PostMapping("/addkegiatan")
    public String addKegiatan(@RequestBody KegiatanDTO kegiatan){
        kegiatanService.insertKegiatan(kegiatan);
        return "berhasil menambahkan kegiatan";
    }

//    @PostMapping("/addmanykegiatan")
//    public List<Kegiatan> addManyKegiatan(@RequestBody KegiatanDTO[] kegiatans){
//        for (KegiatanDTO k : kegiatans){
//            addKegiatan(k);
//        }
//
//        return getAllKegiatan();
//    }

    @PutMapping("/updatekegiatan/{id}")
    public String updateKegiatan(@PathVariable int id,
                                         @RequestBody Kegiatan kegiatan)
            throws ResourceNotExistException{


        kegiatanService.updateKegiatan(id, kegiatan);
        return "data berhasil di update";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteKegiatan(@PathVariable int id){
        kegiatanService.deleteKegiatan(id);
        return "berhasil menghapus data kegiatan";
    }


    private Kegiatan toEntity(KegiatanGetAllDTO kegiatanGetAllDTO){
        return modelMapper.map(kegiatanGetAllDTO, Kegiatan.class);
    }

}
