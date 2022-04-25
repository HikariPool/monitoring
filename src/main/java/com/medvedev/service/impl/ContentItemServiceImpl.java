/*
 * Copyright (c) 19.7.2021 Andrei Medvedev.
 */

package com.medvedev.service.impl;

import com.medvedev.model.dto.ContentItemDto;
import com.medvedev.model.entity.business.ContentItem;
import com.medvedev.model.entity.util.DtoConverter;
import com.medvedev.repository.ContentItemRepo;
import com.medvedev.repository.SessionRepo;
import com.medvedev.service.ContentItemService;
import com.medvedev.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ContentItemServiceImpl implements ContentItemService {
    @Autowired
    private SessionRepo sessionRepo;
    @Autowired
    private ContentItemRepo contentItemRepo;
    @Autowired
    private FileService fileService;


    @Override
    public List<ContentItemDto> getContentItemsBy(Long sessionId) {
        return DtoConverter.convert(contentItemRepo.getBy(sessionId), ContentItemDto.class);
    }

    @Override
    public void create(Long sessionId, MultipartFile image, ContentItem contentItem) throws IOException {
        String imageFileTitle = null;
        if (image != null) {
            imageFileTitle = fileService.write(image.getBytes(),
                    getMemType(image.getOriginalFilename()));
        }

        contentItem.setPreviewPath(imageFileTitle);
        sessionRepo.findById(sessionId).get().getContentItems().add(contentItem);
    }

    private String getMemType(String fileTitle) {
        return fileTitle.substring(fileTitle.lastIndexOf("."));
    }

}
