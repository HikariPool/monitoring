/*
 * Copyright (c) 3.2.2021 Andrei Medvedev.
 */

package com.medvedev.config.data;

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
