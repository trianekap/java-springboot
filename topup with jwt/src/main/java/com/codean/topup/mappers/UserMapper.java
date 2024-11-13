package com.codean.topup.mappers;

import com.codean.topup.models.dtos.UserDto;
import com.codean.topup.models.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    User searchByEmailJwt(String email);

    List<UserDto> getAllByPage(Map<String, Object> params);
    Long countUsers();

    List<User> getAll();
    User getId(Long id);

    void create (User user);
    void update (User user);
    void delete (Long id);
}
