package com.codean.keuangan.services;

import com.codean.keuangan.exceptions.ResourceNotExistException;
import com.codean.keuangan.models.dtos.UserDto;
import com.codean.keuangan.models.dtos.updatedto.UserUpdateDto;
import com.codean.keuangan.models.dtos.pageresult.PageResult;
import com.codean.keuangan.models.dtos.resultmodel.UserAccount;

import java.util.List;

public interface UserService {

    PageResult<UserDto> getAllByPage(int page, int pageSize);

    List<UserDto> getAll() throws ResourceNotExistException;
    UserDto getId(int id) throws ResourceNotExistException;
    List<UserDto> getByUsername(String username);
    List<UserAccount> searchAccountByUsername(String username);
    void create(UserDto userDto);
    void update(int id, UserUpdateDto userUpdateDto) throws ResourceNotExistException;
    void delete(int id) throws ResourceNotExistException;
}
