package com.codean.topup.controllers;

import com.codean.topup.config.JwtTokenUtil;
import com.codean.topup.exceptions.ResourceAlreadyException;
import com.codean.topup.exceptions.ResourceNotExistException;
import com.codean.topup.models.dtos.UserDto;
import com.codean.topup.models.dtos.jwt.JwtRequest;
import com.codean.topup.models.dtos.jwt.JwtResponse;
import com.codean.topup.models.dtos.pageresult.PageResult;
import com.codean.topup.models.responses.ApiResponse;
import com.codean.topup.models.responses.ResponseMessage;
import com.codean.topup.services.UserService;
import com.codean.topup.util.ConvertToFIle;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    ConvertToFIle convertToFIle;

    private void authenticate(String email, String password) throws Exception {
        Objects.requireNonNull(email);
        Objects.requireNonNull(password);
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e){
            throw new Exception("User Disabled");
        } catch (BadCredentialsException e){
            throw new Exception("invalid username password");
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ApiResponse<?> generateToken(@RequestBody JwtRequest authRequest) throws Exception{
        authenticate(authRequest.getEmail(), authRequest.getPassword());

        UserDetails userDetails;

        try {
            userDetails = userDetailsService.loadUserByUsername(authRequest.getEmail());
        } catch (Exception e){
            return ApiResponse.fail(HttpStatus.NOT_FOUND, ResponseMessage.EMAIL_NOTFOUND.getMessages());
        }

        String token = jwtTokenUtil.generateToken(userDetails);
        return ApiResponse.success(HttpStatus.OK, ResponseMessage.RESOURCE_FETCHED.getMessages(), new JwtResponse(token));
    }

    @GetMapping("/getAllByPage")
    public ApiResponse<PageResult<UserDto>> getAllByPage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize) {

        PageResult<UserDto> list;

        try {
            list = userService.getAllByPage(page, pageSize);
        } catch (ResourceNotExistException e){
            return ApiResponse.fail(HttpStatus.NOT_FOUND, ResponseMessage.RESOURCE_NOTFOUND.getMessages());
        }

        return ApiResponse.success(HttpStatus.OK, ResponseMessage.RESOURCE_FETCHED.getMessages(), list);

    }

    @GetMapping("/getAll")
    public ApiResponse<List<UserDto>> getAll() {
        List<UserDto> userDtoList;

        try{
            userDtoList = userService.getAll();
            convertToFIle.saveUserDtoListToTxt(userDtoList);
            convertToFIle.saveUserDtoListToPdf(userDtoList);
            convertToFIle.saveUserDtoListToDocx(userDtoList);
        } catch (ResourceNotExistException e){
            return ApiResponse.fail(HttpStatus.NOT_FOUND, ResponseMessage.RESOURCE_NOTFOUND.getMessages());
        } catch (IOException e) {
            return ApiResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR, "Error saving to file: " + e.getMessage());
        } catch (DocumentException e) {
            return ApiResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR, "Error saving to file: " + e.getMessage());
        } catch (Docx4JException e) {
            throw new RuntimeException(e);
        }

        return ApiResponse.success(HttpStatus.OK,
                ResponseMessage.RESOURCE_FETCHED.getMessages(), userDtoList);
    }

    @GetMapping("/{id}")
    public ApiResponse<Object> getId(@PathVariable Long id) {
        UserDto userDto;
        try{
            userDto = userService.getId(id);
            convertToFIle.saveUserDtoListToTxt(userDto);
        } catch (ResourceNotExistException | IOException e){
            return  ApiResponse.fail(HttpStatus.NOT_FOUND,
                    ResponseMessage.RESOURCE_NOTFOUND.getMessages());
        }

        return ApiResponse.success(HttpStatus.OK,
                ResponseMessage.RESOURCE_FETCHED.getMessages(), userDto);
    }

    @PostMapping("/register")
    public ApiResponse<Object> insert(@RequestBody @Valid UserDto userDto) {

        try {
            userService.create(userDto);
        } catch (ResourceAlreadyException e){
            return ApiResponse.fail(HttpStatus.CONFLICT, ResponseMessage.RESOURCE_ALREADY_EXIST.getMessages());
        }

        return ApiResponse.success(HttpStatus.OK, ResponseMessage.RESOURCE_CREATED.getMessages(), null);
    }

    @PutMapping("/update/{id}")
    public ApiResponse<Object> update(@PathVariable Long id, @RequestBody @Valid UserDto userDto){

        try{
            userService.update(id, userDto);
        } catch (ResourceNotExistException e){
            return ApiResponse.fail(HttpStatus.NOT_FOUND, ResponseMessage.RESOURCE_NOTFOUND.getMessages());
        } catch (ResourceAlreadyException e){
            return ApiResponse.fail(HttpStatus.CONFLICT, ResponseMessage.RESOURCE_CONFLICT.getMessages());
        }

        return ApiResponse.success(HttpStatus.ACCEPTED, ResponseMessage.RESOURCE_MODIFIED.getMessages(), null);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<Object> delete(@PathVariable Long id){
        try{
            userService.delete(id);
        } catch (ResourceNotExistException e){
            return ApiResponse.fail(HttpStatus.NOT_FOUND, ResponseMessage.RESOURCE_NOTFOUND.getMessages());
        }

        return ApiResponse.success(HttpStatus.NO_CONTENT, ResponseMessage.RESOURCE_DELETED.getMessages(), null);
    }
}
