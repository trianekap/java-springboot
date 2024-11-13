package com.codean.topup.services;

import com.codean.topup.exceptions.ResourceAlreadyException;
import com.codean.topup.exceptions.ResourceNotExistException;
import com.codean.topup.models.dtos.UserDto;
import com.codean.topup.models.dtos.pageresult.PageResult;

import java.util.List;

public interface UserService {
    PageResult<UserDto> getAllByPage(int pageNumber, int pageSize) throws ResourceNotExistException;



    List<UserDto> getAll() throws ResourceNotExistException;
    UserDto getId(Long id) throws ResourceNotExistException;

    void create (UserDto userDto) throws ResourceAlreadyException;
    void update (Long id, UserDto userDto) throws ResourceNotExistException, ResourceAlreadyException;
    void delete (Long id) throws ResourceNotExistException;
}
