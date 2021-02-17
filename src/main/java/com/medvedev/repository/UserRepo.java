/*
 * Copyright (c) 9.2.2021 Andrei Medvedev.
 */

package com.medvedev.repository;

import com.medvedev.model.entity.business.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
