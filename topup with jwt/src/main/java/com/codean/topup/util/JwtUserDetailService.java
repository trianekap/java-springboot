package com.codean.topup.util;

import com.codean.topup.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.codean.topup.models.entity.User;

import java.util.ArrayList;

@Service
public class JwtUserDetailService implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        //mencari email yang diinput oleh user dan menyocokan yang ada didatabase
        User user = userMapper.searchByEmailJwt(email);

        if (user.getEmail().equals(email)){
            //Jika email cocok, kita mengembalikan objek UserDetails yang diperlukan oleh Spring Security.
            // membuat instance dari User (dari Spring Security) dengan email dan password pengguna,
            // serta memberikan daftar kosong sebagai otoritas (roles).
            return new org.springframework.security.core.userdetails.User(user.getEmail(),
                    user.getPassword(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User with email "+ email +" not found");
        }

    }
}
