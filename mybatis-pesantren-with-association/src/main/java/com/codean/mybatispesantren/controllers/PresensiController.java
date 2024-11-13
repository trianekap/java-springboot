package com.codean.mybatispesantren.controllers;

import com.codean.mybatispesantren.model.dtos.presensi.PresensiDTO;
import com.codean.mybatispesantren.model.entity.Presensi;
import com.codean.mybatispesantren.services.PresensiService;
import com.codean.mybatispesantren.util.ConvertToFile;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/presensi")
public class PresensiController {


    @Autowired
    PresensiService presensiService;

    @Autowired
    ConvertToFile convertToFile;

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

    @GetMapping("/getpresensi")
    public List<PresensiDTO> getPresensi(@RequestParam(defaultValue = "") String key) throws DocumentException, IOException {
        List<PresensiDTO> presensi = presensiService.searchPresensi(key);
        convertToFile.saveToPdf(presensi);

        return presensi;
    }

    @GetMapping("/getallpresensi")
    public List<Presensi> findAllPresensi(){
        return presensiService.findAllPresensi();
    }

}
