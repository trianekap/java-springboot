package com.codean.topup.controllers;

import com.codean.topup.exceptions.ResourceAlreadyException;
import com.codean.topup.exceptions.ResourceNotExistException;
import com.codean.topup.models.dtos.SaldoDto;
import com.codean.topup.models.dtos.UserDto;
import com.codean.topup.models.dtos.pageresult.PageResult;
import com.codean.topup.models.responses.ApiResponse;
import com.codean.topup.models.responses.ResponseMessage;
import com.codean.topup.services.SaldoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/saldo")
public class SaldoController {

    @Autowired
    private SaldoService saldoService;

    @GetMapping("/getAllByPage")
    public ApiResponse<PageResult<SaldoDto>> getAllByPage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize) {

        PageResult<SaldoDto> list;

        try {
            list = saldoService.getAllByPage(page, pageSize);
        } catch (ResourceNotExistException e){
            return ApiResponse.fail(HttpStatus.NOT_FOUND, ResponseMessage.RESOURCE_NOTFOUND.getMessages());
        }

        return ApiResponse.success(HttpStatus.OK, ResponseMessage.RESOURCE_FETCHED.getMessages(), list);
    }

    @GetMapping("/getAll")
    public ApiResponse<List<SaldoDto>> getAll() {
        List<SaldoDto> saldoDtoList;

        try{
            saldoDtoList = saldoService.getAll();
        } catch (ResourceNotExistException e){
            return ApiResponse.fail(HttpStatus.NOT_FOUND, ResponseMessage.RESOURCE_NOTFOUND.getMessages());
        }

        return ApiResponse.success(HttpStatus.OK,
                ResponseMessage.RESOURCE_FETCHED.getMessages(), saldoDtoList);
    }

    @GetMapping("/{id}")
    public ApiResponse<Object> getId(@PathVariable Long id) {
        SaldoDto saldoDto;
        try{
            saldoDto = saldoService.getId(id);
        } catch (ResourceNotExistException e){
            return  ApiResponse.fail(HttpStatus.NOT_FOUND,
                    ResponseMessage.RESOURCE_NOTFOUND.getMessages());
        }

        return ApiResponse.success(HttpStatus.OK,
                ResponseMessage.RESOURCE_FETCHED.getMessages(), saldoDto);
    }

    @PostMapping("/create")
    public ApiResponse<Object> insert(@RequestBody @Valid SaldoDto saldoDto) {

        try {
            saldoService.create(saldoDto);
        } catch (ResourceAlreadyException e){
            return ApiResponse.fail(HttpStatus.CONFLICT, ResponseMessage.RESOURCE_ALREADY_EXIST.getMessages());
        }

        return ApiResponse.success(HttpStatus.OK, ResponseMessage.RESOURCE_CREATED.getMessages(), null);
    }

    @PutMapping("/update/{idSaldo}")
    public ApiResponse<Object> update(@PathVariable Long idSaldo, @RequestBody @Valid SaldoDto saldoDto){

        try{
            saldoService.update(idSaldo, saldoDto);
        } catch (ResourceNotExistException e) {
            return ApiResponse.fail(HttpStatus.NOT_FOUND, ResponseMessage.RESOURCE_NOTFOUND.getMessages());
        }
        return ApiResponse.success(HttpStatus.ACCEPTED, ResponseMessage.RESOURCE_MODIFIED.getMessages(), null);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<Object> delete(@PathVariable Long id){
        try{
            saldoService.delete(id);
        } catch (ResourceNotExistException e){
            return ApiResponse.fail(HttpStatus.NOT_FOUND, ResponseMessage.RESOURCE_NOTFOUND.getMessages());
        }

        return ApiResponse.success(HttpStatus.NO_CONTENT, ResponseMessage.RESOURCE_DELETED.getMessages(), null);
    }
}
