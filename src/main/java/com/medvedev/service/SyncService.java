/*
 * Copyright (c) 4.8.2021 Andrei Medvedev.
 */

package com.medvedev.service;

import com.medvedev.model.dto.SyncResult;

public interface SyncService {
    SyncResult sync(Long sessionId, Float currentTime);

    void clear();
}
