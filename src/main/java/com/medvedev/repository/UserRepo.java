/*
 * Copyright (c) 9.2.2021 Andrei Medvedev.
 */

package com.medvedev.repository;

import com.medvedev.model.entity.business.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserAccount, Long> {
    UserAccount findByUsername(String username);
}
