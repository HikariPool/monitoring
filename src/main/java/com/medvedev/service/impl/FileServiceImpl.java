/*
 * Copyright (c) 26.2.2021 Andrei Medvedev.
 */

package com.medvedev.service.impl;

import com.medvedev.service.FileService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Value("${path.upload}")
    private String uploadPath;


    @SneakyThrows
    @Override
    public String write(byte[] bytes, String memType) {
        File file = new File(uploadPath);
        if (!file.exists()) {
            file.mkdir();
        }
        String fileTitle = UUID.randomUUID().toString() + memType;

        FileOutputStream output = new FileOutputStream(uploadPath + fileTitle);
        output.write(bytes);
        output.close();

        return fileTitle;
    }
}
