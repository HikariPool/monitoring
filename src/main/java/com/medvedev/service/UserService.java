/*
 * Copyright (c) 9.2.2021 Andrei Medvedev.
 */

package com.medvedev.service;

import com.medvedev.model.entity.business.User;

public interface UserService {
    boolean existsWith(String email, String password);

    void create(User user);
}
