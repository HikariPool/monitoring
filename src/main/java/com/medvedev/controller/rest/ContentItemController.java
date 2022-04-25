/*
 * Copyright (c) 25.4.2022 Andrei Medvedev.
 */

package com.medvedev.controller.rest;

import com.medvedev.model.dto.ContentItemDto;
import com.medvedev.service.ContentItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/session")
public class ContentItemController {

    @Autowired
    private ContentItemService contentItemService;


    @PostMapping("/content/create")
    public void addContentItem(@RequestParam("session_id") Long sessionId, @RequestParam(value = "img", required = false) MultipartFile image, ContentItemDto contentItemDto) {
        String title = contentItemDto.getTitle();
        if (title != null && !title.isEmpty()) {
            contentItemService.create(sessionId, image, ContentItemDto.convertToEntity(contentItemDto));
            return;
        }
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Title is empty!");

        // TODO: 7/20/2021 addingContentItem
    }
}
