/*
 * Copyright (c) 25.2.2021 Andrei Medvedev.
 */

package com.medvedev.service;

import com.medvedev.model.entity.business.Session;
import org.springframework.web.multipart.MultipartFile;

public interface SessionService {
    Session load(long id);

    void create(Session session, MultipartFile image);
}
