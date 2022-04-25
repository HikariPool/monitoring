/*
 * Copyright (c) 25.4.2022 Andrei Medvedev.
 */

package com.medvedev.service;

import com.medvedev.repository.ContentItemRepo;
import com.medvedev.repository.SessionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenericService {
    private static final String SESSION = "session";
    private static final String CONTENT_ITEM = "contentItem";

    @Autowired
    private SessionRepo sessionRepo;
    @Autowired
    private ContentItemRepo contentItemRepo;

    public void remove(Long id, String type) {
        if (SESSION.equals(type)) {
            sessionRepo.deleteParticipantsBySessionId(id);
            contentItemRepo.deleteBySessionId(id);
            sessionRepo.delete(id);
        } else if (CONTENT_ITEM.equals(type)) {
            contentItemRepo.delete(id);
        }
    }
}
