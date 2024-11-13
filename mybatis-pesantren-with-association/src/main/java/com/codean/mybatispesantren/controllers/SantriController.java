package com.codean.mybatispesantren.controllers;


import com.codean.mybatispesantren.config.JwtTokenUtil;
import com.codean.mybatispesantren.exceptions.ResourceNotExistException;
import com.codean.mybatispesantren.model.dtos.jwt.JwtRequest;
import com.codean.mybatispesantren.model.dtos.jwt.JwtResponse;
import com.codean.mybatispesantren.model.dtos.santri.SantriGetDto;
import com.codean.mybatispesantren.model.dtos.santri.SantriUpdateDTO;
import com.codean.mybatispesantren.services.SantriService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/santri/")
public class SantriController {

    private static final Logger log = LoggerFactory.getLogger(SantriController.class);
    @Autowired
    SantriService santriService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @GetMapping("/getallsantri")
    public List<SantriGetDto> getAllSantri() throws ResourceNotExistException {
        List<SantriGetDto> santriDTOS = santriService.findAllSantri();
        if (santriDTOS.isEmpty()){
            String message = "list kosong";
            log.info(message);
            throw new ResourceNotExistException(message);
        }

        return santriService.findAllSantri();
    }

    @GetMapping("/getsantribyid/{idSantri}")
    public SantriGetDto getSantri(@PathVariable int idSantri) throws ResourceNotExistException {
        SantriGetDto santriDTO = santriService.findSantri(idSantri);
        if (santriDTO == null){
            String message = "id tersebut tidak ada";
            log.info(message);
            throw new ResourceNotExistException(message);
        }

        return santriService.findSantri(idSantri);
    }

    @GetMapping("/kehadiran")
    public List<SantriGetDto> getSantriKehadiran() {
        return santriService.findSantriKehadiran();
    }

    @GetMapping("/getsantribyiddetail/{idSantri}")
    public SantriGetDto getSantriDetail(@PathVariable int idSantri) throws ResourceNotExistException {
        return santriService.findDetailedSantri(idSantri);
    }

    @GetMapping("/getsantribyname")
    public List<SantriGetDto> getSantriByName(@RequestParam(defaultValue = "") String name){
        List<SantriGetDto> santriList = santriService.findByName(name);
        return santriList;
    }

    @GetMapping("/getsantribyumur")
    public List<SantriGetDto> getSantriByUmur(@RequestParam(required = false) int umur){
        List<SantriGetDto> santriDTOS = santriService.findByAge(umur);
        return santriDTOS;
    }

    @GetMapping("/getsantribynameorkelas")
    public List<SantriGetDto> getSantriByNameOrKelas(@RequestParam(defaultValue = "") String nama, @RequestParam(defaultValue = "") String kelas){
        List<SantriGetDto> santriList = santriService.findByNameOrKelas(nama, kelas);
        return santriList;
    }

    @PostMapping("/addsantri")
    public String addSantri(@RequestBody SantriGetDto santri){
        santriService.insertSantri(santri);
        return "berhasil menambahkan santri";
    }

    @PostMapping("/addmanysantri")
    public List<SantriGetDto> addManySantri(@RequestBody SantriGetDto[] santris) throws ResourceNotExistException {
        for (SantriGetDto s : santris){
            santriService.insertSantri(s);
        }

        return getAllSantri();
    }

    @PutMapping("/updatesantri/{id}")
    public List<SantriGetDto> updateSantri(@PathVariable int id,
                                           @RequestBody SantriUpdateDTO santriUpdateDTO)
            throws ResourceNotExistException {


        santriService.updateSantri(id, santriUpdateDTO);

        return getAllSantri();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id) throws ResourceNotExistException {
        santriService.deleteSantri(id);
        return "berhasil menghapus data santri";
    }

    private void authenticate(String username, String password) throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e){
            throw new Exception("User Disabled");
        } catch (BadCredentialsException e){
            throw new Exception("invalid username password");
        }
    }

    // ######################## BY PAST AUTH ###################################
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest authRequest) throws Exception{
        authenticate(authRequest.getUsername(), authRequest.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
