package com.codean.keuangan.services.Impl;

import com.codean.keuangan.exceptions.ResourceNotExistException;
import com.codean.keuangan.mappers.UserMapper;
import com.codean.keuangan.models.dtos.UserDto;
import com.codean.keuangan.models.dtos.updatedto.UserUpdateDto;
import com.codean.keuangan.models.dtos.pageresult.PageResult;
import com.codean.keuangan.models.dtos.resultmodel.UserAccount;
import com.codean.keuangan.models.entity.User;
import com.codean.keuangan.services.UserService;
import com.codean.keuangan.util.PaginationHelper;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserMapper userMapper;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public PageResult<UserDto> getAllByPage(int page, int pageSize) {

        if (page < 1) page = 1;
        if (pageSize < 1) pageSize = 10;

        int offset = (page - 1) * pageSize;

        Map<String, Object> params = new HashMap<>();
        params.put("offset", offset);
        params.put("limit", pageSize);
        List<UserDto> users = userMapper.getAllByPage(params);

        Long totalElement = userMapper.countUsers();

        int totalPage = PaginationHelper.calculateTotalPages(totalElement, pageSize);

        boolean isLastPage = page >= totalPage;

        PageResult<UserDto> pageResult = new PageResult<>();
        pageResult.setContent(users);
        pageResult.setPage_number(page);
        pageResult.setPage_size(pageSize);
        pageResult.setTotal_element(totalElement);
        pageResult.setTotal_page(totalPage);
        pageResult.setLast_page(isLastPage);

        return pageResult;
    }

    @Override
    public List<UserDto> getAll() throws ResourceNotExistException {
        return userMapper.getAll().stream().map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getId(int id) throws ResourceNotExistException{
        User user = userMapper.getId(id);

        if (user == null){
            String message = "that id not exist";
            log.error(message);
            throw new ResourceNotExistException(message);
        }

        return toDto(user);
    }

    @Override
    public List<UserDto> getByUsername(String username){
        List<User> userList = userMapper.searchByUsername(username);

        return userList.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<UserAccount> searchAccountByUsername(String username) {
        List<UserAccount> userAccounts = userMapper.searchAccountByUsername(username);

        return userAccounts.stream().collect(Collectors.toList());
    }

    @Override
    public void create(UserDto userDto) {

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());

        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        userMapper.create(user);
    }

    @Override
    public void update(int id, UserUpdateDto userUpdateDto) throws ResourceNotExistException {
        User user = userMapper.getId(id);

        if (user == null){
            String message = "that id not exist";
            log.error(message);
            throw new ResourceNotExistException(message);
        }

        user.setUsername(userUpdateDto.getUsername());
        user.setPassword(userUpdateDto.getPassword());

        user.setPassword(passwordEncoder.encode(userUpdateDto.getPassword()));

        userMapper.update(user);
    }

    @Override
    public void delete(int id) throws ResourceNotExistException{
        User user = userMapper.getId(id);
        if (user == null){
            String message = "that id not exist";
            log.error(message);
            throw new ResourceNotExistException(message);
        }

        userMapper.delete(id);
    }


    private UserDto toDto(User user){
        return modelMapper.map(user, UserDto.class);
    }

    private User toEntity(UserDto userDto){
        return modelMapper.map(userDto, User.class);
    }
}
