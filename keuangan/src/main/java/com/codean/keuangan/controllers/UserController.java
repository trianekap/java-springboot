package com.codean.keuangan.controllers;


import com.codean.keuangan.configs.JwtTokenUtil;
import com.codean.keuangan.exceptions.ResourceNotExistException;
import com.codean.keuangan.models.dtos.UserDto;
import com.codean.keuangan.models.dtos.jwt.JwtRequest;
import com.codean.keuangan.models.dtos.jwt.JwtResponse;
import com.codean.keuangan.models.dtos.updatedto.UserUpdateDto;
import com.codean.keuangan.models.dtos.pageresult.PageResult;
import com.codean.keuangan.models.dtos.resultmodel.UserAccount;
import com.codean.keuangan.services.UserService;
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

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    UserDetailsService userDetailsService;


    @GetMapping("/getAllByPage")
    public PageResult<UserDto> getAllByPage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize) {

        return userService.getAllByPage(page, pageSize);
    }

    @PostMapping("/create")
    public String create(@RequestBody @Valid UserDto userDto){
        userService.create(userDto);
        return "user has been added";
    }

    @GetMapping("/getAll")
    public List<UserDto> getAll() throws ResourceNotExistException{
        List<UserDto> userDtoList = userService.getAll();
        if (userDtoList.isEmpty()){
            String message = "list is empty";
            log.info(message);
            throw new ResourceNotExistException(message);
        }

        return userService.getAll();
    }

    @GetMapping("/getId/{id}")
    public UserDto getId(@PathVariable int id) throws ResourceNotExistException{
        UserDto user = userService.getId(id);
        return user;
    }

    @GetMapping("/searchByName")
    public List<UserDto> getByName(@RequestParam(defaultValue = "") String username){
        List<UserDto> userDtoList = userService.getByUsername(username);

        return userDtoList.stream().collect(Collectors.toList());
    }

    @GetMapping("/searchAccountByUsername")
    public List<UserAccount> searchAccountByUsername(@RequestParam(defaultValue = "") String username){
        List<UserAccount> userAccounts = userService.searchAccountByUsername(username);

        return userAccounts.stream().collect(Collectors.toList());
    }

    @PutMapping("/update/{id}")
    public List<UserDto> update(@PathVariable int id,
                                @RequestBody @Valid UserUpdateDto userUpdateDto)
            throws ResourceNotExistException{

        userService.update(id, userUpdateDto);

        return getAll();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id) throws ResourceNotExistException{
        userService.delete(id);

        return "user has been deleted";
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

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest authRequest) throws Exception{
        authenticate(authRequest.getUsername(), authRequest.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
