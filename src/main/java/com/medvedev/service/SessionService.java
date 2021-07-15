/*
 * Copyright (c) 25.2.2021 Andrei Medvedev.
 */

package com.medvedev.service;

import com.medvedev.model.dto.SessionDTO;
import com.medvedev.model.entity.business.Session;
import com.medvedev.model.entity.business.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SessionService {
    Session load(long id);

    void create(Session session, MultipartFile image);

    List<SessionDTO> getByUser(User user);
}
