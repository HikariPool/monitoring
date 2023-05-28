package com.sahno.model.entity.business;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class DashboardRes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String result;
}
