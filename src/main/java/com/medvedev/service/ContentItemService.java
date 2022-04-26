/*
 * Copyright (c) 19.7.2021 Andrei Medvedev.
 */

package com.medvedev.service;

import com.medvedev.model.dto.ContentItemDto;
import com.medvedev.model.entity.business.ContentItem;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ContentItemService {

    List<ContentItemDto> getContentItemsBy(Long sessionId);

    @Transactional
    void create(Long sessionId, MultipartFile image, MultipartFile media, ContentItem contentItem) throws IOException;
}
