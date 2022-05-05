/*
 * Copyright (c) 28.4.2022 Andrei Medvedev.
 */

package com.medvedev.repository;

import com.medvedev.model.entity.business.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepo extends JpaRepository<Message, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM MESSAGES WHERE SESSION_ID = ?")
    List<Message> findBySessionId(Long sessionId);
}
