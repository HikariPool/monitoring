/*
 * Copyright (c) 25.2.2021 Andrei Medvedev.
 */

package com.medvedev.service.impl;

import com.medvedev.model.dto.SessionDTO;
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

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {
    @Autowired
    private FileService fileService;
    @Autowired
    private SessionRepo sessionRepo;
    @Autowired
    private EntityManagerFactory entityManagerFactory;


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
        session.setImagePath(fileTitle);
        List<User> users = new ArrayList<>();
        users.add(user);
        session.setParticipants(users);


        sessionRepo.save(session);

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Query query = entityManager.createNativeQuery("INSERT INTO PARTICIPANTS VALUES(:user_id, :session_id)");
        query.setParameter("user_id", user.getId());
        query.setParameter("session_id", session.getId());
        query.executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<SessionDTO> getByUser(User user) {
        List<SessionDTO> dtos = new ArrayList<>();
        for (Session session : sessionRepo.getByUser(user.getId())) {
            dtos.add(SessionDTO.convertToDto(session));
        }
        return dtos;
    }

    private String getMemType(String fileTitle) {
        return fileTitle.substring(fileTitle.lastIndexOf("."));
    }
}