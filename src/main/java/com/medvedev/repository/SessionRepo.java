/*
 * Copyright (c) 25.2.2021 Andrei Medvedev.
 */

package com.medvedev.repository;

import com.medvedev.model.entity.business.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepo extends JpaRepository<Session, Long> {
}
