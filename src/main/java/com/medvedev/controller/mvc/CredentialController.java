/*
 * Copyright (c) 3.8.2020 Andrey Medvedev.
 */

package com.medvedev.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/credential")
public class CredentialController {

    @GetMapping("/signIn")
    public String getSignInPage() {
        return "sign_in";
    }

    @GetMapping("/signUp")
    public String getSignUpPage() {
        return "sign_up";
    }
}
