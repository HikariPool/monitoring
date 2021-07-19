/*
 * Copyright (c) 19.7.2021 Andrei Medvedev.
 */

package com.medvedev.service.impl;

import com.medvedev.model.dto.ContentItemDto;
import com.medvedev.model.entity.util.DtoConverter;
import com.medvedev.repository.ContentItemRepo;
import com.medvedev.service.ContentItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentItemServiceImpl implements ContentItemService {
    @Autowired
    private ContentItemRepo contentItemRepo;


    @Override
    public List<ContentItemDto> getContentItemsBy(Long sessionId) {
        return DtoConverter.convert(contentItemRepo.getBy(sessionId), ContentItemDto.class);
    }
}
