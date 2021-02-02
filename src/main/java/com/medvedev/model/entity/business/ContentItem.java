/*
 * Copyright (c) 2.2.2021 Andrei Medvedev.
 */

package com.medvedev.model.entity.business;

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
    @Column(nullable = false)
    private String contentTitle;
    private String contentPreview;
}
//// TODO: 2/2/2021 add lists