package com.codean.topup.controllers;

import com.codean.topup.exceptions.ResourceAlreadyException;
import com.codean.topup.exceptions.ResourceNotExistException;
import com.codean.topup.models.dtos.MataUangDto;
import com.codean.topup.models.dtos.UserDto;
import com.codean.topup.models.dtos.pageresult.PageResult;
import com.codean.topup.models.responses.ApiResponse;
import com.codean.topup.models.responses.ResponseMessage;
import com.codean.topup.services.MataUangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/mataUang")
public class MataUangController {

    @Autowired
    private MataUangService mataUangService;

    @GetMapping("/getAllByPage")
    public ApiResponse<PageResult<MataUangDto>> getAllByPage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize) {

        PageResult<MataUangDto> list;

        try {
            list = mataUangService.getAllByPage(page, pageSize);
        } catch (ResourceNotExistException e){
            return ApiResponse.fail(HttpStatus.NOT_FOUND, ResponseMessage.RESOURCE_NOTFOUND.getMessages());
        }

        return ApiResponse.success(HttpStatus.OK, ResponseMessage.RESOURCE_FETCHED.getMessages(), list);
    }

    @GetMapping("/getAll")
    public ApiResponse<List<MataUangDto>> getAll() {
        List<MataUangDto> mataUangDtoList;
        try {
            mataUangDtoList= mataUangService.getAll();
        } catch (ResourceNotExistException e){
            return ApiResponse.fail(HttpStatus.NOT_FOUND, ResponseMessage.RESOURCE_NOTFOUND.getMessages());
        }

        return ApiResponse.success(HttpStatus.OK, ResponseMessage.RESOURCE_FETCHED.getMessages(), mataUangDtoList);
    }

    @GetMapping("/{id}")
    public ApiResponse<MataUangDto> getById(@PathVariable Long id) {
        MataUangDto mataUangDto;
        try {
            mataUangDto = mataUangService.getById(id);
        } catch (ResourceNotExistException e){
            return ApiResponse.fail(HttpStatus.NOT_FOUND, ResponseMessage.RESOURCE_NOTFOUND.getMessages());
        }

        return ApiResponse.success(HttpStatus.OK, ResponseMessage.RESOURCE_FETCHED.getMessages(), mataUangDto);
    }

    @PostMapping("/create")
    public ApiResponse<Object> insert(@RequestBody @Valid MataUangDto mataUangDto) {

        try {
            mataUangService.create(mataUangDto);
        } catch (ResourceAlreadyException e){
            return ApiResponse.fail(HttpStatus.CONFLICT, ResponseMessage.RESOURCE_ALREADY_EXIST.getMessages());
        }

        return ApiResponse.success(HttpStatus.CREATED, ResponseMessage.RESOURCE_CREATED.getMessages(), null);
    }

    @PutMapping("/update/{id}")
    public ApiResponse<Object> update(@PathVariable Long id, @RequestBody @Valid MataUangDto mataUangDto) throws ResourceNotExistException {

        try {
            mataUangService.update(id, mataUangDto);
        } catch (ResourceNotExistException e){
            return ApiResponse.fail(HttpStatus.NOT_FOUND, ResponseMessage.RESOURCE_NOTFOUND.getMessages());
        } catch (ResourceAlreadyException e){
            return ApiResponse.fail(HttpStatus.CONFLICT, ResponseMessage.RESOURCE_CONFLICT.getMessages());
        }

        return ApiResponse.success(HttpStatus.ACCEPTED, ResponseMessage.RESOURCE_CREATED.getMessages(), null);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<Object> delete(@PathVariable Long id) throws ResourceNotExistException{
        try {
            mataUangService.delete(id);
        } catch (ResourceNotExistException e){
            return ApiResponse.fail(HttpStatus.NOT_FOUND, ResponseMessage.RESOURCE_NOTFOUND.getMessages());
        }
        return ApiResponse.success(HttpStatus.NO_CONTENT, ResponseMessage.RESOURCE_DELETED.getMessages(), null);
    }

}
