package com.zemoso.springbootassignment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/showMyLoginPage")
    public String showMyLoginPage() {
        return "fancy-login";
    }

    @GetMapping("/access-denied-page")
    public String showAccessDeniedPage() {
        return "access-denied-page";
    }
}
