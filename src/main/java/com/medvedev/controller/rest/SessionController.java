/*
 * Copyright (c) 25.2.2021 Andrei Medvedev.
 */

package com.medvedev.controller.rest;

import com.medvedev.model.dto.ContentItemDto;
import com.medvedev.model.dto.SessionDTO;
import com.medvedev.model.entity.business.User;
import com.medvedev.service.ContentItemService;
import com.medvedev.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/session")
public class SessionController {
    @Autowired
    private SessionService sessionService;
    @Autowired
    private ContentItemService contentItemService;


    @PostMapping("/create")
    public void create(@RequestParam(value = "img", required = false) MultipartFile image, SessionDTO sessionDTO) {
        String title = sessionDTO.getTitle();
        if (title != null && !title.isEmpty()) {
            sessionService.create(SessionDTO.convertToEntity(sessionDTO), image);
            return;
        }
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Title is empty!");
    }

    @GetMapping("/all")
    public List<SessionDTO> getAll() {
        User currentUser = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return sessionService.getByUser(currentUser);
    }

    @GetMapping("/content/{session_id}")
    public List<ContentItemDto> getContentBy(@PathVariable("session_id") Long sessionId) {
        return contentItemService.getContentItemsBy(sessionId);
    }
}
