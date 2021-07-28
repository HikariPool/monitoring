/*
 * Copyright (c) 28.7.2021 Andrei Medvedev.
 */

package com.medvedev.controller.rest;

import com.medvedev.service.FileResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stream")
public class StreamController {
    @Autowired
    private FileResourceService fileResourceService;


    @GetMapping(value = "/get/{source}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public FileSystemResource stream(@PathVariable String source) {
        return fileResourceService.getByTitle(source);
    }

    @PostMapping("/sync/{sessionId}/{time}")
    public void sync(@PathVariable String sessionId, @PathVariable Float time) {
        //// TODO: 7/28/2021 sync
    }
}
