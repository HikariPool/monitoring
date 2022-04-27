/*
 * Copyright (c) 9.2.2021 Andrei Medvedev.
 */

package com.medvedev.repository;

import com.medvedev.model.entity.business.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);

    List<User> findByEmail(String email);
}
