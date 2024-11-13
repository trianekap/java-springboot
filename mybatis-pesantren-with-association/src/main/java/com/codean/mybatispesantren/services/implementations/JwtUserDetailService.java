package com.codean.mybatispesantren.services.implementations;

import com.codean.mybatispesantren.mapper.SantriMapper;
import com.codean.mybatispesantren.model.entity.Santri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailService implements UserDetailsService {

    @Autowired
    SantriMapper santriMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Santri santri = santriMapper.findByUsername(username);

        if (santri.getUsername().equals(username)){
            return new User(santri.getUsername(), santri.getPassword(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User with username "+username+" not found");
        }

    }
}
