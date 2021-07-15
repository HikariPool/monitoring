/*
 * Copyright (c) 14.7.2021 Andrei Medvedev.
 */

package com.medvedev.service;

import org.springframework.core.io.FileSystemResource;

public interface FileResourceService {
    FileSystemResource getByTitle(String title);
}
