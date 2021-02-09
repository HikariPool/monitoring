/*
 * Copyright (c) 9.2.2021 Andrei Medvedev.
 */

package com.medvedev.data.config;

import com.medvedev.data.audit.Auditor;
import com.medvedev.model.entity.business.UserAccount;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class JpaConfig {
    @Bean
    public AuditorAware<UserAccount> auditorAware() {
        return new Auditor();
    }
}
