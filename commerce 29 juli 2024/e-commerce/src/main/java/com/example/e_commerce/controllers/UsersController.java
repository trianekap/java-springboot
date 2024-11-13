package com.example.e_commerce.controllers;


import com.example.e_commerce.exceptions.ResourceInvalidConstraintViolation;
import com.example.e_commerce.exceptions.ResourceNotExistException;
import com.example.e_commerce.model.dtos.ResponseDTO.UsersResponseDTO;
import com.example.e_commerce.model.responses.ApiResponses;
import com.example.e_commerce.model.responses.ResponseMessages;
import com.example.e_commerce.model.dtos.UsersAddDTO;
import com.example.e_commerce.model.dtos.UsersGetDTO;
import com.example.e_commerce.model.entities.Users;
import com.example.e_commerce.exceptions.NonExitenceResourceException;
import com.example.e_commerce.exceptions.ResourceAlreadyExistException;
import com.example.e_commerce.services.UsersService;
import org.hibernate.mapping.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class UsersController {

    private static final Logger log = LoggerFactory.getLogger(UsersController.class);
    @Autowired
    UsersService usersService;

    @PostMapping("/adduser")
    public ApiResponses<Object> addUser(@Valid @RequestBody UsersAddDTO usersAddDto) throws ResourceAlreadyExistException, ResourceNotExistException{

        UsersGetDTO users = usersService.addUsers(usersAddDto);
        return ApiResponses.success(HttpStatus.OK, ResponseMessages.RESOURCE_CREATED.getMessages(), null);
    }

    @GetMapping("/getalluserspagination")
    public ResponseEntity<UsersResponseDTO> getUsersByPagination(
            @RequestParam int page_number,
            @RequestParam int page_size,
            @RequestParam String sort_order,
            @RequestParam(defaultValue = "mobilenumber") String sort_by
            ){

        UsersResponseDTO usersResponseDTO = usersService.getAllUsers(page_number, page_size, sort_order, sort_by);

        return new ResponseEntity<UsersResponseDTO>(usersResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/getuserbykeyword")
    public ApiResponses<List<UsersGetDTO>> getUserByEmail(@RequestParam String keyword)
            throws ResourceNotExistException{

        List<UsersGetDTO> usersGetDTO = usersService.getUserByKeyword(keyword);
        if (usersGetDTO.isEmpty()){
            String message = "kata kunci tidak ditemukan";
            log.warn(message);
            return ApiResponses.fail(HttpStatus.NOT_FOUND,
                    ResponseMessages.RESOURCE_FETCHED.getMessages());
        }

        return ApiResponses.success(HttpStatus.OK, ResponseMessages.RESOURCE_FETCHED.getMessages(),
                usersGetDTO);
    }

    @GetMapping("/getuserbyid/{user_id}")
    public ApiResponses<Object> getUsersById(@PathVariable UUID user_id, UsersGetDTO usersGetDTO) throws ResourceNotExistException{

         UsersGetDTO users = usersService.getUsersById(user_id, usersGetDTO);

        return ApiResponses.success(HttpStatus.OK, ResponseMessages.RESOURCE_FETCHED.getMessages(), users);
    }


    @PutMapping("/updateuserbyid/{user_id}")
    public ApiResponses<Object> updateUsersById(@PathVariable UUID user_id, @RequestBody UsersGetDTO usersGetDTO) throws ResourceNotExistException, ResourceAlreadyExistException, ResourceInvalidConstraintViolation {

        usersService.updateUsersById(user_id, usersGetDTO);

        return ApiResponses.success(HttpStatus.ACCEPTED, ResponseMessages.RESOURCE_MODIFIED.getMessages(), null);
    }

    @DeleteMapping("/deleteuser/{user_id}")
    public ApiResponses<Users> deleteUsersById(@PathVariable UUID user_id) throws ResourceNotExistException{

        usersService.deleteUsersById(user_id);
        return ApiResponses.success(HttpStatus.OK, ResponseMessages.RESOURCE_DELETED.getMessages(), null);
    }

}
