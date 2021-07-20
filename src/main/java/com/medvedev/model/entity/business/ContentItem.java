/*
 * Copyright (c) 2.2.2021 Andrei Medvedev.
 */

package com.medvedev.model.entity.business;

import com.medvedev.data.constants.Constants;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "content_items")
public class ContentItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    @Column(name = "item_order")
    private Long order;
    private String previewPath;
    private String sourcePath;


    @PostLoad
    private void postLoad() {
        previewPath = Constants.UPLOAD_URL + previewPath;
    }
}
