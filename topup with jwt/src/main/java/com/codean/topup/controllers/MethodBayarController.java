package com.codean.topup.controllers;

import com.codean.topup.exceptions.ResourceAlreadyException;
import com.codean.topup.exceptions.ResourceNotExistException;
import com.codean.topup.models.dtos.MethodBayarDto;
import com.codean.topup.models.dtos.UserDto;
import com.codean.topup.models.dtos.pageresult.PageResult;
import com.codean.topup.models.responses.ApiResponse;
import com.codean.topup.models.responses.ResponseMessage;
import com.codean.topup.services.MethodBayarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/methodBayar")
public class MethodBayarController {

    @Autowired
    private MethodBayarService methodBayarService;

    @GetMapping("/getAllByPage")
    public ApiResponse<PageResult<MethodBayarDto>> getAllByPage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize) {

        PageResult<MethodBayarDto> list;

        try {
            list = methodBayarService.getAllByPage(page, pageSize);
        } catch (ResourceNotExistException e){
            return ApiResponse.fail(HttpStatus.NOT_FOUND, ResponseMessage.RESOURCE_NOTFOUND.getMessages());
        }

        return ApiResponse.success(HttpStatus.OK, ResponseMessage.RESOURCE_FETCHED.getMessages(), list);
    }

    @GetMapping("/getAll")
    public ApiResponse<List<MethodBayarDto>> getAll() throws ResourceNotExistException {
        List<MethodBayarDto> list;

        try {
            list = methodBayarService.getAll();
        } catch (ResourceNotExistException e){
            return ApiResponse.fail(HttpStatus.NOT_FOUND, ResponseMessage.RESOURCE_NOTFOUND.getMessages());
        }
        return ApiResponse.success(HttpStatus.OK, ResponseMessage.RESOURCE_FETCHED.getMessages(), list);
    }

    @GetMapping("/{id}")
    public ApiResponse<MethodBayarDto> getById(@PathVariable Long id) throws ResourceNotExistException {
        MethodBayarDto methodBayarDto;

        try {
            methodBayarDto = methodBayarService.getById(id);
        } catch (ResourceNotExistException e){
            return ApiResponse.fail(HttpStatus.NOT_FOUND, ResponseMessage.RESOURCE_NOTFOUND.getMessages());
        }

        return ApiResponse.success(HttpStatus.OK, ResponseMessage.RESOURCE_FETCHED.getMessages(), methodBayarDto);
    }

    @PostMapping("/create")
    public ApiResponse<Object> insert(@RequestBody @Valid MethodBayarDto methodBayarDto) {

        try {
            methodBayarService.create(methodBayarDto);
        } catch (ResourceAlreadyException e){
            return ApiResponse.fail(HttpStatus.CONFLICT, ResponseMessage.RESOURCE_CONFLICT.getMessages());
        }

        return ApiResponse.success(HttpStatus.OK, ResponseMessage.RESOURCE_CREATED.getMessages(), null);
    }

    @PutMapping("/update/{id}")
    public ApiResponse<Object> update(@PathVariable Long id, @RequestBody @Valid MethodBayarDto methodBayarDto) throws ResourceNotExistException {

        try {
            methodBayarService.update(id, methodBayarDto);
        } catch (ResourceNotExistException e){
            return ApiResponse.fail(HttpStatus.NOT_FOUND, ResponseMessage.RESOURCE_NOTFOUND.getMessages());
        } catch (ResourceAlreadyException e){
            return ApiResponse.fail(HttpStatus.CONFLICT, ResponseMessage.RESOURCE_CONFLICT.getMessages());
        }

        return ApiResponse.success(HttpStatus.OK, ResponseMessage.RESOURCE_MODIFIED.getMessages(), null);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<Object> delete(@PathVariable Long id) throws ResourceNotExistException{

        try {
            methodBayarService.delete(id);
        } catch (ResourceNotExistException e){
            return ApiResponse.fail(HttpStatus.NOT_FOUND, ResponseMessage.RESOURCE_NOTFOUND.getMessages());
        }

        return ApiResponse.success(HttpStatus.OK, ResponseMessage.RESOURCE_DELETED.getMessages(), null);
    }

}
