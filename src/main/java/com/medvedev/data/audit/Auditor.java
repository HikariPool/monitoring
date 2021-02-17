/*
 * Copyright (c) 9.2.2021 Andrei Medvedev.
 */

package com.medvedev.data.audit;

import com.medvedev.model.entity.business.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class Auditor implements AuditorAware<User> {
    @Override
    public Optional<User> getCurrentAuditor() {
        return Optional.of((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}