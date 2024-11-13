package com.codean.perpustakaan.controllers;

import com.codean.perpustakaan.models.dtos.BukuDTO;
import com.codean.perpustakaan.services.BukuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/buku")
@RequiredArgsConstructor
public class BukuController {

    private final BukuService bukuService;

    @GetMapping
    public ResponseEntity<List<BukuDTO>> findAll(){
        return ResponseEntity.ok(bukuService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BukuDTO> findById(@PathVariable UUID id){
        return ResponseEntity.ok().body(bukuService.findById(id));
    }

    @GetMapping("/byname")
    public ResponseEntity<List<BukuDTO>> findByName(String judul){
        List<BukuDTO> bukuDTOS = bukuService.findByJudul(judul);
        return ResponseEntity.ok(bukuDTOS);
    }

    @PostMapping("/save")
    public ResponseEntity<HttpStatus> save(@Valid @RequestBody BukuDTO bukuDTO){
        bukuService.save(bukuDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<HttpStatus> update(@RequestBody BukuDTO bukuDTO){
        bukuService.update(bukuDTO);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<HttpStatus> delete(@PathVariable UUID id){
        bukuService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
