/*
 * Copyright (c) 14.7.2021 Andrei Medvedev.
 */

package com.medvedev.service.impl;

import com.medvedev.data.constants.Constants;
import com.medvedev.service.FileResourceService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class FileResourceServiceImpl implements FileResourceService {

    public FileSystemResource getByTitle(String title) {
        return new FileSystemResource(new File(Constants.UPLOAD_PATH + title));
    }
}
