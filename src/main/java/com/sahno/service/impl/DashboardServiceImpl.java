package com.sahno.service.impl;

import com.sahno.model.dto.DashboardDto;
import com.sahno.model.entity.business.Dashboard;
import com.sahno.repository.DashboardRepo;
import com.sahno.repository.DashboardResRepo;
import com.sahno.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardServiceImpl implements DashboardService {
    @Autowired
    private DashboardRepo dashboardRepo;
    @Autowired
    private DashboardResRepo dashboardResRepo;


    @Override
    public List<DashboardDto> getAll() {
        List<DashboardDto> res = new ArrayList<>();
        List<Dashboard> dashboards = dashboardRepo.findAll();
        dashboards.stream().forEach(dashboard -> {
            DashboardDto dto = DashboardDto.convertToDto(dashboard);
            dto.setCountRes(dashboard.getResults().size());
            res.add(dto);
        });
        return res;
    }

    @Override
    public void updateQuery(Long id, String query) {
        dashboardRepo.findById(id).get().setQuery(query);
        dashboardRepo.flush();
    }

    @Override
    public void refresh(Long id) {
        dashboardResRepo.clear(id);
        Dashboard dashboard = dashboardRepo.findById(id).get();

    }

    @Override
    public void refreshAll(){
        dashboardRepo.findAll().stream().forEach(dashboard -> refresh(dashboard.getId()));
    }
}
