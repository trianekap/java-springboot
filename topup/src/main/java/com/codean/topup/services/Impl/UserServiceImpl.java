package com.codean.topup.services.Impl;

import com.codean.topup.exceptions.ResourceAlreadyException;
import com.codean.topup.exceptions.ResourceNotExistException;
import com.codean.topup.mappers.UserMapper;
import com.codean.topup.models.dtos.UserDto;
import com.codean.topup.models.dtos.pageresult.PageResult;
import com.codean.topup.models.entity.User;
import com.codean.topup.services.UserService;
import com.codean.topup.util.PaginationHelper;
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

    private static final Logger log = LoggerFactory.getLogger(BayarServiceImpl.class);

    @Autowired
    UserMapper userMapper;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public PageResult<UserDto> getAllByPage(int pageNumber, int pageSize) {

        if (pageNumber < 1) pageNumber = 1;
        if (pageSize < 1) pageSize = 10;

        int offset = (pageNumber - 1) * pageSize;

        Map<String, Object> params = new HashMap<>();
        params.put("offset", offset);
        params.put("limit", pageSize);
        List<UserDto> users = userMapper.getAllByPage(params);

        Long totalElement = userMapper.countUsers();

        int totalPage = PaginationHelper.calculateTotalPages(totalElement, pageSize);

        boolean isLastPage;
        isLastPage = pageNumber >= totalPage;

        PageResult<UserDto> pageResult = new PageResult<>();
        pageResult.setContent(users);
        pageResult.setPage_number(pageNumber);
        pageResult.setPage_size(pageSize);
        pageResult.setTotal_element(totalElement);
        pageResult.setTotal_page(totalPage);
        pageResult.setLast_page(isLastPage);

        return pageResult;
    }

    @Override
    public List<UserDto> getAll() throws ResourceNotExistException {
        List<User> userList = userMapper.getAll();

        if (userList == null){
            throw new ResourceNotExistException("list is empty!");
        }

        return userList.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public UserDto getId(Long id) throws ResourceNotExistException {
        User user = userMapper.getId(id);

        if (user == null){
            throw new ResourceNotExistException("user with id " + id + " not found!");
        }

        return toDto(user);
    }

    @Override
    public void create(UserDto userDto) throws ResourceAlreadyException {

        User user = new User();
        user.setNamaUser(userDto.getNamaUser());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        userMapper.create(user);
    }

    @Override
    public void update(Long id, UserDto userDto) throws ResourceNotExistException, ResourceAlreadyException {
        User user = userMapper.getId(id);

        if (user == null){
            throw new ResourceNotExistException("user with id " + id + " not found!");
        }

        user.setNamaUser(userDto.getNamaUser());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        user.setPassword(passwordEncoder.encode(userDto.getPassword()));


        userMapper.update(user);
    }

    @Override
    public void delete(Long id) throws ResourceNotExistException {
        User user = userMapper.getId(id);

        if (user == null){
            throw new ResourceNotExistException("user with id " + id + " not found!");
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
