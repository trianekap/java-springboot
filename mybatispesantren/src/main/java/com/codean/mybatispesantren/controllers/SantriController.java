package com.codean.mybatispesantren.controllers;


import com.codean.mybatispesantren.mapper.SantriMapper;
import com.codean.mybatispesantren.model.gets.SantriKehadiran;
import com.codean.mybatispesantren.model.post.Santri;
import com.codean.mybatispesantren.services.SantriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/santri/")
public class SantriController {

    @Autowired
    SantriMapper santriMapper;

    @Autowired
    SantriService santriService;

    @GetMapping("/getallsantri")
    public List<Santri> getAllSantri(){
        return santriMapper.findAllSantri();
    }

    @GetMapping("/getsantribyid/{idSantri}")
    public Santri getSantri(@PathVariable int idSantri){
        return santriMapper.findSantri(idSantri);
    }

    @GetMapping("/kehadiran")
    public List<Santri> getSantriKehadiran() {
        return santriService.findSantriKehadiran();
    }

    @GetMapping("/getsantribyiddetail/{idSantri}")
    public Santri getSantriDetail(@PathVariable int idSantri){
        return santriMapper.findDetailedSantri(idSantri);
    }

    @GetMapping("/getsantribyname")
    public List<Santri> getSantriByName(@RequestParam String name){
        List<Santri> santriList = santriMapper.findByName(name);
        return santriList;
    }

    @GetMapping("/getsantribynameorkelas")
    public List<Santri> getSantriByNameOrKelas(@RequestParam String name, @RequestParam String kelas){
        List<Santri> santriList = santriMapper.findByNameOrKelas(name, kelas);
        return santriList;
    }

    @PostMapping("/addsantri")
    public String addSantri(Santri santri){
        santriMapper.insertSantri(santri);
        return "berhasil menambahkan santri";
    }

    @PostMapping("/addmanysantri")
    public List<Santri> addManySantri(@RequestBody Santri[] santris){
        for (Santri s : santris){
            santriMapper.insertSantri(s);
        }

        return getAllSantri();
    }

    @PutMapping("/updatesantri/{id}")
    public List<Santri> updateSantri(@PathVariable int id, @RequestBody Santri santri){
        santriMapper.updateSantri(id, santri);
        return getAllSantri();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id){
        santriMapper.deleteSantri(id);
        return "berhasil menghapus data santri";
    }
}
