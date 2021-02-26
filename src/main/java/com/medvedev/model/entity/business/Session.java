/*
 * Copyright (c) 2.2.2021 Andrei Medvedev.
 */

package com.medvedev.model.entity.business;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "sessions")
@EntityListeners(AuditingEntityListener.class)
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String title;
    private String fileTitle;

    @CreatedBy
    @ManyToOne
    private User createdBy;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id")
    private List<ContentItem> contentItems;

    @ManyToMany(mappedBy = "sessions")
    private List<User> participants;
}
