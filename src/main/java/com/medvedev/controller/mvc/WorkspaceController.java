/*
 * Copyright (c) 11.8.2020 Andrey Medvedev.
 */

package com.medvedev.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/workspace")
public class WorkspaceController {
    @GetMapping("/page")
    public String getWorkspacePage() {
        return "workspace";
    }
}
