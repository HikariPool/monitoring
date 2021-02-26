/*
 * Copyright (c) 25.2.2021 Andrei Medvedev.
 */

package com.medvedev.controller.rest;

import com.medvedev.model.dto.SessionDTO;
import com.medvedev.service.SessionService;
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
public class SessionController {
    @Autowired
    private SessionService sessionService;


    @PostMapping("/create")
    public void create(@RequestParam(value = "img", required = false) MultipartFile image, SessionDTO sessionDTO) {
        String title = sessionDTO.getTitle();
        if (title != null && !title.isEmpty()) {
            sessionService.create(SessionDTO.convertToEntity(sessionDTO), image);
            return;
        }
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Title is empty!");
    }
}
