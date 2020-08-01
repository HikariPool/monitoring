/*
 * Copyright (c) 1.8.2020 Andrey Medvedev.
 */

package com.medvedev.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/credential")
public class CredentialController {

    @GetMapping("/page")
    public String getLoginPage() {
        return "credential_page";
    }

    @PostMapping("/sign_in")
    public String signIn() {
        return "";//// TODO: 8/1/2020 paste post sign in page
    }

    @PostMapping("/sing_up")
    public void signUp() {
        return;//// TODO: 8/1/2020 paste post sign in page
    }
}
