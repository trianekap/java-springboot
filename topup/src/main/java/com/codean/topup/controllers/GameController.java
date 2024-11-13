package com.codean.topup.controllers;

import com.codean.topup.exceptions.ResourceAlreadyException;
import com.codean.topup.exceptions.ResourceNotExistException;
import com.codean.topup.models.dtos.GameDto;
import com.codean.topup.models.dtos.resultmodel.GameDtoList;
import com.codean.topup.models.responses.ApiResponse;
import com.codean.topup.models.responses.ResponseMessage;
import com.codean.topup.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/getAllList")
    public ApiResponse<List<Object>> gameResultListMap() {
        List<GameDtoList> gameDtoList;

        try {
            gameDtoList = gameService.gameResultListMap();
        } catch (ResourceNotExistException e){
            return ApiResponse.fail(HttpStatus.NOT_FOUND, ResponseMessage.RESOURCE_NOTFOUND.getMessages());
        }

        return ApiResponse.success(HttpStatus.OK,
                ResponseMessage.RESOURCE_FETCHED.getMessages(), Collections.singletonList(gameDtoList));
    }

    @GetMapping("/getAll")
    public ApiResponse<List<GameDto>> getAll() {
        List<GameDto> gameDtoList;

        try{
            gameDtoList = gameService.getAll();
        } catch (ResourceNotExistException e){
            return ApiResponse.fail(HttpStatus.NOT_FOUND, ResponseMessage.RESOURCE_NOTFOUND.getMessages());
        }

        return ApiResponse.success(HttpStatus.OK,
                ResponseMessage.RESOURCE_FETCHED.getMessages(), gameDtoList);
    }

    @GetMapping("/{id}")
    public ApiResponse<Object> getById(@PathVariable Long id) {
        GameDto gameDto;
        try{
           gameDto = gameService.getById(id);
        } catch (ResourceNotExistException e){
            return  ApiResponse.fail(HttpStatus.NOT_FOUND,
                    ResponseMessage.RESOURCE_NOTFOUND.getMessages());
        }

        return ApiResponse.success(HttpStatus.OK,
                ResponseMessage.RESOURCE_FETCHED.getMessages(), gameDto);
    }

    @PostMapping("/create")
    public ApiResponse<Object> insert(@RequestBody GameDto gameDto) {

        try {
            gameService.create(gameDto);
        } catch (ResourceAlreadyException e){
            return ApiResponse.fail(HttpStatus.CONFLICT, ResponseMessage.RESOURCE_ALREADY_EXIST.getMessages());
        }

        return ApiResponse.success(HttpStatus.OK, ResponseMessage.RESOURCE_CREATED.getMessages(), null);
    }

    @PutMapping("/update/{id}")
    public ApiResponse<Object> update(@PathVariable Long id, @RequestBody GameDto gameDto){

        try{
           gameService.update(id, gameDto);
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
            gameService.delete(id);
        } catch (ResourceNotExistException e){
            return ApiResponse.fail(HttpStatus.NOT_FOUND, ResponseMessage.RESOURCE_NOTFOUND.getMessages());
        }

        return ApiResponse.success(HttpStatus.NO_CONTENT, ResponseMessage.RESOURCE_DELETED.getMessages(), null);
    }

}
