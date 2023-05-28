package com.sahno.model.dto;

import com.sahno.model.entity.business.Dashboard;
import com.sahno.model.entity.business.DashboardRes;
import com.sahno.model.entity.business.User;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class DashboardDto {
    private Long id;
    private String name;
    private long countRes;
    private List<DashboardResDto> dashboardResDtos;


    public static DashboardDto convertToDto(Dashboard dashboard) {
        DashboardDto res = new ModelMapper().map(dashboard, DashboardDto.class);
        if (dashboard.getResults() != null){
            res.dashboardResDtos = dashboard.getResults().stream().map(DashboardResDto::convertToDto).collect(Collectors.toList());
        }
        return res;
    }

    public static Dashboard convertToEntity(DashboardDto dashboardDto) {
        return new ModelMapper().map(dashboardDto, Dashboard.class);
    }
}
