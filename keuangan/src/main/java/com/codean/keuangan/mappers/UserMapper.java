package com.codean.keuangan.mappers;

import com.codean.keuangan.models.dtos.UserDto;
import com.codean.keuangan.models.dtos.resultmodel.UserAccount;
import com.codean.keuangan.models.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    List<UserDto> getAllByPage(Map<String, Object> params);

    Long countUsers();

    User searchByUsernameJwt(String username);


    List<User> getAll();
    User getId(int id);
    List<User> searchByUsername(String username);
    List<UserAccount> searchAccountByUsername(String username);
    void create(User user);
    void update(User user);
    void delete(int id);
}
