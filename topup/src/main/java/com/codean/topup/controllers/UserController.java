package com.codean.topup.controllers;

import com.codean.topup.exceptions.ResourceAlreadyException;
import com.codean.topup.exceptions.ResourceNotExistException;
import com.codean.topup.models.dtos.UserDto;
import com.codean.topup.models.dtos.pageresult.PageResult;
import com.codean.topup.models.responses.ApiResponse;
import com.codean.topup.models.responses.ResponseMessage;
import com.codean.topup.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

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
        } catch (ResourceNotExistException e){
            return ApiResponse.fail(HttpStatus.NOT_FOUND, ResponseMessage.RESOURCE_NOTFOUND.getMessages());
        }

        return ApiResponse.success(HttpStatus.OK,
                ResponseMessage.RESOURCE_FETCHED.getMessages(), userDtoList);
    }

    @GetMapping("/{id}")
    public ApiResponse<Object> getId(@PathVariable Long id) {
        UserDto userDto;
        try{
            userDto = userService.getId(id);
        } catch (ResourceNotExistException e){
            return  ApiResponse.fail(HttpStatus.NOT_FOUND,
                    ResponseMessage.RESOURCE_NOTFOUND.getMessages());
        }

        return ApiResponse.success(HttpStatus.OK,
                ResponseMessage.RESOURCE_FETCHED.getMessages(), userDto);
    }

    @PostMapping("/create")
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
