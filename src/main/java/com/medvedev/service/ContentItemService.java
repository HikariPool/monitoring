/*
 * Copyright (c) 19.7.2021 Andrei Medvedev.
 */

package com.medvedev.service;

import com.medvedev.model.dto.ContentItemDto;

import java.util.List;

public interface ContentItemService {

    List<ContentItemDto> getContentItemsBy(Long sessionId);
}
