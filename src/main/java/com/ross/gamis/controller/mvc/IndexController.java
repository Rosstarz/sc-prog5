package com.ross.gamis.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IndexController {
    @GetMapping()
    public String getIndexPage() {
        return "index";
    }

    @GetMapping("/404")
    public String getNotFound() {
        return "404";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }
}
