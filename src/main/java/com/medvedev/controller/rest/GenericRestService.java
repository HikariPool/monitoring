/*
 * Copyright (c) 25.4.2022 Andrei Medvedev.
 */

package com.medvedev.controller.rest;

import com.medvedev.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/generic")
public class GenericRestService {
    @Autowired
    private GenericService genericService;


    @PostMapping("/remove")
    public void remove(@RequestParam("id") Long id, @RequestParam("type") String type) {
        genericService.remove(id, type);
    }
}
