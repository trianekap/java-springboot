package com.codean.mybatispesantren.controllers;


import com.codean.mybatispesantren.mapper.PresensiMapper;
import com.codean.mybatispesantren.services.PresensiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/presensi")
public class PresensiController {

    @Autowired
    PresensiMapper presensiMapper;

    @Autowired
    PresensiService presensiService;

    @PostMapping("register/{idSantri}/{idKegiatan}/{status}")
    public ResponseEntity<Object> register(@PathVariable int idSantri,
                                           @PathVariable int idKegiatan,
                                           @PathVariable String status){
        presensiService.register(idSantri, idKegiatan, status);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("cancel/{idSantri}/{idKegiatan}/{status}")
    public ResponseEntity<Object> cancelRegistration(@PathVariable int idSantri,
                                                      @PathVariable int idKegiatan,
                                                     @PathVariable String status){
        presensiService.cancelRegister(idSantri, idKegiatan, status);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
