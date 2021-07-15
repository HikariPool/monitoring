/*
 * Copyright (c) 25.2.2021 Andrei Medvedev.
 */

package com.medvedev.repository;

import com.medvedev.model.entity.business.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepo extends JpaRepository<Session, Long> {
    @Query(nativeQuery = true,
            value = "SELECT * FROM Sessions WHERE id IN(SELECT session_id FROM Participants WHERE user_id = :userId)")
    List<Session> getByUser(Long userId);
}