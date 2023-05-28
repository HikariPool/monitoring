package com.sahno.model.dto;

import com.sahno.model.entity.business.Dashboard;
import com.sahno.model.entity.business.User;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class DashboardDto {
    private Long id;
    private String name;
    private long countRes;


    public static DashboardDto convertToDto(Dashboard dashboard) {
        return new ModelMapper().map(dashboard, DashboardDto.class);
    }

    public static Dashboard convertToEntity(DashboardDto dashboardDto) {
        return new ModelMapper().map(dashboardDto, Dashboard.class);
    }
}
