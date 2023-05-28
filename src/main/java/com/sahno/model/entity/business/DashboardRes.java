package com.sahno.model.entity.business;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DashboardRes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String result;
}
