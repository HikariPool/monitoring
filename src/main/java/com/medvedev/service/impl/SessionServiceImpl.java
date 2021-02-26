/*
 * Copyright (c) 25.2.2021 Andrei Medvedev.
 */

package com.medvedev.service.impl;

import com.medvedev.model.entity.business.Session;
import com.medvedev.model.entity.business.User;
import com.medvedev.repository.SessionRepo;
import com.medvedev.service.FileService;
import com.medvedev.service.SessionService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SessionServiceImpl implements SessionService {
    @Autowired
    private FileService fileService;
    @Autowired
    private SessionRepo sessionRepo;


    @Override
    public Session load(long id) {
        return sessionRepo.getOne(id);
    }

    @SneakyThrows
    @Override
    public void create(Session session, MultipartFile image) {
        String fileTitle = null;
        if (image != null) {
            fileTitle = fileService.write(image.getBytes(),
                    getMemType(image.getOriginalFilename()));
        }

        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        session.setCreatedBy(user);
        session.setFileTitle(fileTitle);

        sessionRepo.save(session);
    }

    private String getMemType(String fileTitle) {
        return fileTitle.substring(fileTitle.lastIndexOf("."));
    }
}
