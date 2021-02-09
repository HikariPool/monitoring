/*
 * Copyright (c) 9.2.2021 Andrei Medvedev.
 */

package com.medvedev.data.audit;

import com.medvedev.model.entity.business.UserAccount;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class Auditor implements AuditorAware<UserAccount> {
    @Override
    public Optional<UserAccount> getCurrentAuditor() {
        return Optional.of((UserAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}