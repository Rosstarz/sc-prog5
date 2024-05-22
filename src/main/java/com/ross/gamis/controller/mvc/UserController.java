package com.ross.gamis.controller.mvc;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/account")
    public String getMyAccount(){
        return "user/index";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }
}
