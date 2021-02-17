/*
 * Copyright (c) 9.2.2021 Andrei Medvedev.
 */

package com.medvedev.service.impl;

import com.medvedev.model.entity.business.User;
import com.medvedev.repository.UserRepo;
import com.medvedev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder encoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + username + " not found!");
        }
        return user;
    }

    @Override
    public boolean existsWith(String username, String password) {
        User user = userRepo.findByUsername(username);
        if (user != null) {
            return encoder.matches(password, user.getPassword());
        }
        return false;
    }

    @Override
    public void create(User user) {
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepo.save(user);
    }
}
