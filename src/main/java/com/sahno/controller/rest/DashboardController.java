package com.sahno.controller.rest;

import com.sahno.model.dto.DashboardDto;
import com.sahno.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
    @Autowired
    private DashboardService dashboardService;


    @GetMapping("/all")
    public List<DashboardDto> getAll(){
        return dashboardService.getAll();
    }
}
