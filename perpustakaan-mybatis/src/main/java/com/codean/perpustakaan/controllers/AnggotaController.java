package com.codean.perpustakaan.controllers;


import com.codean.perpustakaan.models.dtos.AnggotaDTO;
import com.codean.perpustakaan.services.AnggotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/anggota")
public class AnggotaController {

    private final AnggotaService anggotaService;

    @GetMapping
    public ResponseEntity<List<AnggotaDTO>> findAll(){
        return ResponseEntity.ok(anggotaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnggotaDTO> findById(@PathVariable UUID id){
        return ResponseEntity.ok().body(anggotaService.findById(id));
    }

    @GetMapping("/byname")
    public ResponseEntity<List<AnggotaDTO>> findByName(String name){
        List<AnggotaDTO> anggotaDTOList = anggotaService.findByName(name);
        return ResponseEntity.ok(anggotaDTOList);
    }

    @PostMapping("/save")
    public ResponseEntity<HttpStatus> save(@Valid @RequestBody AnggotaDTO anggotaDTO){
        anggotaService.save(anggotaDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<HttpStatus> update(@RequestBody AnggotaDTO anggotaDTO){
        anggotaService.update(anggotaDTO);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<HttpStatus> delete(@PathVariable UUID id){
        anggotaService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
