/*
 * Copyright (c) 28.4.2022 Andrei Medvedev.
 */

package com.medvedev.controller.rest;

import com.medvedev.model.entity.business.Message;
import com.medvedev.repository.ChatRepo;
import com.medvedev.repository.SessionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private ChatRepo chatRepo;
    @Autowired
    private SessionRepo sessionRepo;


    @GetMapping("/get")
    public List<Message> get(@RequestParam("session_id") Long sessionId) {
        return chatRepo.findBySessionId(sessionId);
    }

    @PostMapping("/create")
    public void create(@RequestParam("session_id") Long sessionId, Message message) {
        message.setSession(sessionRepo.findById(sessionId).get());

        chatRepo.saveAndFlush(message);
    }
}
