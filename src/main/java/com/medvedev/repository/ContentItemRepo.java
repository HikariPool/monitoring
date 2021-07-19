/*
 * Copyright (c) 19.7.2021 Andrei Medvedev.
 */

package com.medvedev.repository;

import com.medvedev.model.entity.business.ContentItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentItemRepo extends JpaRepository<ContentItem, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM content_items WHERE session_id = ? ORDER BY item_order")
    List<ContentItem> getBy(Long sessionId);
}
