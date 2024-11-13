package com.codean.perpustakaan.controllers;

import com.codean.perpustakaan.models.dtos.PinjamDTO;
import com.codean.perpustakaan.models.dtos.PinjamPostDTO;
import com.codean.perpustakaan.services.PinjamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/pinjam")
@RestController
public class PinjamController {

    private final PinjamService pinjamService;

    @GetMapping
    public ResponseEntity<List<PinjamDTO>> findAll(){
        return ResponseEntity.ok(pinjamService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PinjamDTO> findById(@PathVariable UUID id){
        return ResponseEntity.ok().body(pinjamService.findById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<HttpStatus> save(@Valid @RequestBody PinjamPostDTO pinjamPostDTO){
        pinjamService.save(pinjamPostDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<HttpStatus> update(@RequestBody PinjamDTO pinjamDTO){
        pinjamService.update(pinjamDTO);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<HttpStatus> delete(@PathVariable UUID id){
        pinjamService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
