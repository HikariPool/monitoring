/*
 * Copyright (c) 4.8.2021 Andrei Medvedev.
 */

package com.medvedev.service.impl;

import com.medvedev.model.dto.SessionSyncDto;
import com.medvedev.model.dto.SyncResult;
import com.medvedev.model.entity.business.User;
import com.medvedev.service.ContentItemService;
import com.medvedev.service.SyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class SyncServiceImpl implements SyncService {
    private final Map<Long, SessionSyncDto> sessionStates = new HashMap<>();
    @Value("${enabled_range}")
    private Float ENABLED_RANGE;
    @Autowired
    private ContentItemService contentItemService;


    @Override
    public SyncResult sync(Long sessionId, Float currentTime) {
        //// TODO: 8/4/2021 setting current content item
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        if (sessionStates.containsKey(sessionId)) {
            return getSyncResult(user.getId(), sessionId, currentTime);
        } else {
            sessionStates.put(sessionId, new SessionSyncDto(user.getId(), currentTime));
            return sync(sessionId, currentTime);
        }
    }

    @Override
    public void clear() {
        sessionStates.clear();
    }

    private SyncResult getSyncResult(Long userId, Long sessionId, Float userCurrentTime) {
        SyncResult result = new SyncResult();

        SessionSyncDto syncDto = sessionStates.get(sessionId);
        Optional<Map.Entry<Long, Float>> earliestTime = syncDto.getEarliestTimeWithout(userId);

        if (earliestTime.isPresent()) {
            result.setNeedPlay(!(earliestTime.get().getValue() + ENABLED_RANGE < userCurrentTime));
        }
        syncDto.getUserTimes().put(userId, userCurrentTime);

        return result;
    }
}
